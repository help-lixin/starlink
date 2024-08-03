package help.lixin.starlink.core.plugin;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import help.lixin.starlink.core.dto.PropertySource;
import help.lixin.starlink.core.plugin.service.IPropertySourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.web.context.support.StandardServletEnvironment;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class PluginNamedContextFactory<C extends Specification> //
    implements DisposableBean, ApplicationContextAware, SmartLifecycle {

    private Logger logger = LoggerFactory.getLogger(PluginNamedContextFactory.class);

    /**
     * 插件名称.实例名称.属性名称=属性值 例如: jsch.jsch-instance-1.host=192.168.1.10
     */
    private static final String PROPERTIES_KEY_FORMAT = "%s.%s.%s";

    private final String propertySourceName;

    private final String plugin;

    private final Map<String, AnnotationConfigApplicationContext> contexts = new ConcurrentHashMap<>();

    private Map<String, C> configurations = new ConcurrentHashMap<>();

    private ApplicationContext parent;

    private Class<?> defaultConfigType;
    private static final String INSTANCE = "instance";
    private static final String PLUGIN = "plugin";

    private final ConcurrentHashMap<String /** key: instance */
        , String /** property_hash */
    > instancePropertiesHashHold = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String /** key:instance */
        , //
        Map<String, Object> /** key:pluginCode.dev.group-erp.instance-1.url value: 192.168.8.8:8080 */
    > //
    instancePropertiesHold = new ConcurrentHashMap<>();
    private final AtomicBoolean isRunning = new AtomicBoolean(false);

    // 待处理的数据源
    private final Map<String /** key:instance */
        , PropertySource /** { instanceCode:'jsch-instance-1', propertiesHash:123 , content:{} } */
    > //
    allPropertySources = new ConcurrentHashMap<>();
    // 重试记录
    private final ConcurrentHashMap<String /** key:instance */
        , AtomicInteger /** 1 */
    > retryRecord = new ConcurrentHashMap<>();
    // 最大重试次数
    private Integer maxRetry = 10;

    private long initialDelay = 5L;
    private long period = 60L;
    private TimeUnit unit = TimeUnit.SECONDS;
    private final Object lock = new Object();

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor( //
        new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                String threadName = String.format("%s-plugin-init-thread", plugin);
                Thread t = new Thread(r);
                t.setDaemon(true);
                t.setName(threadName);
                return t;
            }
        });

    public PluginNamedContextFactory(Class<?> defaultConfigType, //
        String propertySourceName, //
        String plugin) {
        this(defaultConfigType, //
            propertySourceName, //
            plugin, //
            null, //
            null, //
            null, //
            null);
    }

    public PluginNamedContextFactory(Class<?> defaultConfigType, //
        String propertySourceName, //
        String plugin, //
        Integer maxRetry, //
        Long initialDelay, //
        Long period, TimeUnit unit) {
        this.defaultConfigType = defaultConfigType;
        this.propertySourceName = propertySourceName;
        this.plugin = plugin;
        if (null != maxRetry) {
            this.maxRetry = maxRetry;
        }
        if (null != initialDelay) {
            this.initialDelay = initialDelay;
        }
        if (null != period) {
            this.period = period;
        }
        if (null != unit) {
            this.unit = unit;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext parent) throws BeansException {
        this.parent = parent;
    }

    public void setConfigurations(List<C> configurations) {
        for (C client : configurations) {
            this.configurations.put(client.getName(), client);
        }
    }

    public Set<String> getContextNames() {
        return new HashSet<>(this.contexts.keySet());
    }

    @Override
    public void destroy() {
        Collection<AnnotationConfigApplicationContext> values = this.contexts.values();
        for (AnnotationConfigApplicationContext context : values) {
            context.close();
        }
        this.contexts.clear();
    }

    protected AnnotationConfigApplicationContext getContext(String instanceCode) {
        if (!this.contexts.containsKey(instanceCode)) {
            synchronized (this.lock) {
                if (!this.contexts.containsKey(instanceCode)) {
                    this.contexts.put(instanceCode, createContext(instanceCode));
                }
            }
        }
        return this.contexts.get(instanceCode);
    }

    protected AnnotationConfigApplicationContext createContext(String instanceCode) {
        // 创建一个子上下文(ApplicationContext)
        AnnotationConfigApplicationContext childContext = new AnnotationConfigApplicationContext();
        if (this.configurations.containsKey(instanceCode)) {
            for (Class<?> configuration : this.configurations.get(instanceCode).getConfiguration()) {
                childContext.register(configuration);
            }
        }

        for (Map.Entry<String, C> entry : this.configurations.entrySet()) {
            if (entry.getKey().startsWith("default.")) {
                for (Class<?> configuration : entry.getValue().getConfiguration()) {
                    childContext.register(configuration);
                }
            }
        }
        childContext.register(PropertyPlaceholderAutoConfiguration.class, this.defaultConfigType);

        // 定义子上下文(ApplicationContext)中的配置(Propertiyes)信息
        Map<String, Object> sources = new HashMap<>();
        sources.put(INSTANCE, instanceCode);
        sources.put(PLUGIN, plugin);

        // 在子上下文(ApplicationContext)中添加一个配置(Propertiyes)文件
        // 实际此时的Propertiyes只有两项(例如):
        // instance = jenkins-insatince-1
        // plugin = jenkins
        MutablePropertySources propertySources = childContext.getEnvironment().getPropertySources();
        propertySources.addFirst(new MapPropertySource(this.propertySourceName, sources));

        // 当子上下文(ApplicationContext)与web结合时,需要删除掉:configurationProperties
        // 需要移除: parent里的configurationProperties
        if (parent.getEnvironment() instanceof StandardServletEnvironment) {
            StandardServletEnvironment standardServletEnvironment = (StandardServletEnvironment)parent.getEnvironment();
            MutablePropertySources tmpSurces =
                ((ConfigurableEnvironment)standardServletEnvironment).getPropertySources();
            tmpSurces.remove("configurationProperties");
        }

        // 尝试看下配置是否存在.
        if (!instancePropertiesHold.containsKey(instanceCode)) {
            IPropertySourceService propertySourceService = parent.getBean(IPropertySourceService.class);
            List<PropertySource> pullResult = propertySourceService.pull(plugin, instanceCode);
            applyPropertySource(pullResult, false);
        }

        // 根据instanceCode,查看是否有配置(Propertiyes)文件,如果,有的话,则,把这个配置文件添加到子上下文里(ApplicationContext)
        Map<String, Object> instancePropertySourceMap = instancePropertiesHold.get(instanceCode);
        if (Objects.nonNull(instancePropertySourceMap)) {
            propertySources.addFirst(new MapPropertySource(instanceCode, instancePropertySourceMap));
        }

        if (this.parent != null) {
            // Spring的ApplicationContext本来就是一个典型的组合模式来着的,子容器是可以访问父容器的Bean,但是,父容器是不允许访问父容器的Bean来着的.
            childContext.setParent(this.parent);
            childContext.setClassLoader(this.parent.getClassLoader());
        }
        childContext.setDisplayName(generateDisplayName(instanceCode));
        childContext.refresh();
        return childContext;
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void start() {
        if (isRunning.compareAndSet(false, true)) {
            boolean tryLoad = true;
            IPropertySourceService propertySourceService = parent.getBean(IPropertySourceService.class);
            String pluginCode = plugin;
            scheduledExecutorService.scheduleAtFixedRate(() -> {
                synchronized (lock) {
                    List<PropertySource> pullResult = propertySourceService.pull(pluginCode, "*");
                    if (null != pullResult && !pullResult.isEmpty()) {
                        // 1. 提取新的:PropertySource
                        List<PropertySource> newPropertySources =
                            PluginNamedContextFactory.this.extractNewPropertySource(pullResult);
                        // 2. 提取hash值不同的:PropertySource(需要重新将容器,给关闭,并创建新的容器来着.)
                        List<PropertySource> differencePropertySources =
                            PluginNamedContextFactory.this.extraceDifference(pullResult);
                        // 3. 根据配置,初始化容器
                        if (!newPropertySources.isEmpty()) {
                            // 3.1 加载容器
                            applyPropertySource(newPropertySources, tryLoad);

                            allPropertySources.putAll(newPropertySources //
                                .stream() //
                                .collect(Collectors.toMap(PropertySource::getInstanceCode,
                                    propertySource -> propertySource)));
                        }

                        // 4. 销毁容器,并创建新的容器.
                        if (!differencePropertySources.isEmpty()) {
                            // 4.1 从容器中
                            differencePropertySources //
                                .stream() //
                                .map(PropertySource::getInstanceCode) //
                                .forEach((instnaceCode) -> { //
                                    AnnotationConfigApplicationContext ctx = contexts.get(instnaceCode);
                                    if (null != ctx) {
                                        ctx.close();
                                        contexts.remove(instnaceCode);
                                    }
                                });

                            // 重新加载容器
                            applyPropertySource(differencePropertySources, tryLoad);

                            allPropertySources.putAll(differencePropertySources //
                                .stream() //
                                .collect(Collectors.toMap(PropertySource::getInstanceCode,
                                    propertySource -> propertySource)));
                        }
                    }
                }
            }, initialDelay, period, unit);
        }
    }

    protected List<PropertySource> extraceDifference(List<PropertySource> pullResult) {
        List<PropertySource> differencePropertySources = pullResult //
            .stream() //
            .filter((newPropertySource) -> { // 1.要求最新取回来的配置信息里必须有:instanceCode/propertiesHash
                String instanceCode = newPropertySource.getInstanceCode();
                String propertiesHash = newPropertySource.getPropertiesHash();
                if (null != instanceCode && null != propertiesHash) {
                    return true;
                }
                return false;
            }) //
            .filter((newPropertySource) -> {
                PropertySource oldPropertySource = allPropertySources.get(newPropertySource.getInstanceCode());
                if (null != oldPropertySource) {
                    // 2. 比较:allPropertySources里的:hash与newPropertySource里的hash
                    String oldPropertiesHash = oldPropertySource.getPropertiesHash();
                    return !oldPropertiesHash.equals(newPropertySource.getPropertiesHash());
                }
                return false;
            }) //
            .collect(Collectors.toList());
        return differencePropertySources;
    }

    protected List<PropertySource> extractNewPropertySource(List<PropertySource> pullResult) {
        List<PropertySource> newPropertySources = pullResult //
            .stream() //
            .filter((newPropertySource) -> { // 1.要求最新取回来的配置信息里必须有:instanceCode/propertiesHash
                String instanceCode = newPropertySource.getInstanceCode();
                String propertiesHash = newPropertySource.getPropertiesHash();
                if (null != instanceCode && null != propertiesHash) {
                    return true;
                }
                return false;
            }) //
            .filter((newPropertySource) -> {
                PropertySource oldPropertySource = allPropertySources.get(newPropertySource.getInstanceCode());
                return null == oldPropertySource;
            }) //
            .collect(Collectors.toList());
        return newPropertySources;
    }

    protected void applyPropertySource(List<PropertySource> pullResult, boolean tryLoad) {
        // String instanceFormat = "%s";
        // 拉取最新的配置信息列表
        List<PropertySource> waitApplyPropertySources = pullResult.stream() //
            .map(propertySource -> {
                // 实例唯一id的hash code
                String propertiesHash = propertySource.getPropertiesHash();

                String instancePropertiesHash = instancePropertiesHashHold.get(propertySource.getInstanceCode());
                if (Objects.nonNull(instancePropertiesHash)) {
                    if (!instancePropertiesHash.equals(propertiesHash)) { // 比较hash,hash不同,则添加到待处理列表
                        return propertySource;
                    }
                }
                // 为空的情况下,添加到待处理列表
                return propertySource;
            }) //
            .collect(Collectors.toList());

        // 把待处理的信息,添加到global级map里
        waitApplyPropertySources //
            .stream() //
            .forEach(propertySource -> {
                String propertiesHash = propertySource.getPropertiesHash();
                // json体
                String jsonContent = propertySource.getContent();

                Map<String, Object> instancePropertySource = new HashMap();
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    Map<String, Object> tempMap = objectMapper.readValue(jsonContent, Map.class);
                    tempMap.forEach((key, value) -> {
                        String newInstancePropertyKey =
                            String.format(PROPERTIES_KEY_FORMAT, plugin, propertySource.getInstanceCode(), key);
                        instancePropertySource.put(newInstancePropertyKey, value);
                    });
                    instancePropertiesHold.put(propertySource.getInstanceCode(), instancePropertySource);
                    instancePropertiesHashHold.put(propertySource.getInstanceCode(), propertiesHash);
                    if (tryLoad) {
                        tryLoadBean(propertySource.getInstanceCode(), defaultConfigType);
                    }
                } catch (Exception ignore) {
                    logger.warn("加载实例:[{}]配置出现,错误信息如下:[\n{}\n]", propertySource.getInstanceCode(), ignore.getMessage());
                }
            });
    }

    protected void tryLoadBean(String instance, Class<?> clazz) {
        try {
            getInstance(instance, clazz);
        } catch (Exception e) {
            logger.error("加载Bean:[{}]出现错误,错误信息如下:[{}]", clazz, e);
        }
    }

    @Override
    public void stop() {
        isRunning.set(Boolean.FALSE);
        scheduledExecutorService.shutdownNow();
    }

    @Override
    public boolean isRunning() {
        return isRunning.get();
    }

    protected String generateDisplayName(String name) {
        return this.getClass().getSimpleName() + "-" + name;
    }

    public <T> T getInstance(String name, Class<T> type) {
        AnnotationConfigApplicationContext context = getContext(name);
        try {
            return context.getBean(type);
        } catch (NoSuchBeanDefinitionException e) {
            throw new RuntimeException("无法找到类:" + e.getMessage());
        }
    }

    public <T> ObjectProvider<T> getLazyProvider(String name, Class<T> type) {
        return new PluginClientFactoryObjectProvider<>(this, name, type);
    }

    public <T> ObjectProvider<T> getProvider(String name, Class<T> type) {
        AnnotationConfigApplicationContext context = getContext(name);
        return context.getBeanProvider(type);
    }

    public <T> T getInstance(String name, Class<?> clazz, Class<?>... generics) {
        ResolvableType type = ResolvableType.forClassWithGenerics(clazz, generics);
        return getInstance(name, type);
    }

    @SuppressWarnings("unchecked")
    public <T> T getInstance(String name, ResolvableType type) {
        AnnotationConfigApplicationContext context = getContext(name);
        String[] beanNames = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(context, type);
        if (beanNames.length > 0) {
            for (String beanName : beanNames) {
                if (context.isTypeMatch(beanName, type)) {
                    return (T)context.getBean(beanName);
                }
            }
        }
        return null;
    }

    public <T> Map<String, T> getInstances(String name, Class<T> type) {
        AnnotationConfigApplicationContext context = getContext(name);
        return BeanFactoryUtils.beansOfTypeIncludingAncestors(context, type);
    }
}
