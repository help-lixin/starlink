package help.lixin.starlink.core.plugin.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.starlink.core.dto.PropertySource;
import help.lixin.starlink.core.plugin.service.IPropertySourceService;
import help.lixin.utils.Md5Crypt;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 解析本地配置文件
 */
public class LocalPropertySourceService implements IPropertySourceService {

    private Logger logger = LoggerFactory.getLogger(LocalPropertySourceService.class);

    private final Environment environment;

    public LocalPropertySourceService(Environment environment) {
        this.environment = environment;
    }

    @Override
    public List<PropertySource> pull(String pluginCode, String instanceCode) {
        List<PropertySource> results = new ArrayList<>(0);
        // jsch.instances=jsch-instance-1,jsch-instance-2
        String instances = environment.getProperty(String.format("%s.instances", pluginCode));
        if (StringUtils.isNotBlank(instances)) {
            String[] instanceArray = instances.split(",");
            for (String instance : instanceArray) {
                // jsch.jsch-instance-1
                String propertyPrefix = String.format("%s.%s", pluginCode, instance);
                // 过滤出:environment中包含着指定的前缀,转换成json
                Map<String, Object> instanceMap = extractProperty(propertyPrefix);
                try {
                    ObjectMapper objectMapper = new ObjectMapper();
                    String jsonContent = objectMapper.writeValueAsString(instanceMap);

                    PropertySource propertySource = new PropertySource();
                    propertySource.setContent(jsonContent);
                    propertySource.setPluginCode(pluginCode);
                    propertySource.setInstanceCode(instance);
                    String bodyHash = Md5Crypt.md5Crypt(jsonContent);
                    propertySource.setPropertiesHash(bodyHash);
                    results.add(propertySource);
                } catch (Exception e) {
                    logger.error("解析:[{}]出现错误,错误详细信息如下:[\n{}\n]", propertyPrefix, e.getMessage());
                }
                }
            }
        return results;
    }

    protected Map<String, Object> extractProperty(String propertyPrefix) {
        Map<String, Object> properties = new HashMap<>();
        if (environment instanceof ConfigurableEnvironment) {
            for (org.springframework.core.env.PropertySource<?> propertySource : ((ConfigurableEnvironment) environment).getPropertySources()) {
                if (propertySource instanceof EnumerablePropertySource) {
                    for (String key : ((EnumerablePropertySource) propertySource).getPropertyNames()) {
                        if (key.startsWith(propertyPrefix)) {
                            // 要去掉前缀
                            String newKey = key.substring(propertyPrefix.length() + 1);
                            properties.put(newKey, propertySource.getProperty(key));
                        }
                    }
                }
            }
        }
        return properties;
    }


}