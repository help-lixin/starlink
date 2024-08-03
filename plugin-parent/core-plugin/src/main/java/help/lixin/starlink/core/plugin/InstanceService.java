package help.lixin.starlink.core.plugin;

import java.lang.reflect.ParameterizedType;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/13 7:53 下午
 * @Description
 */
@Deprecated
public class InstanceService<T> {

    protected PluginNamedContextFactory pluginNamedContextFactory;

    protected T getApi(String instance) {
        return (T) pluginNamedContextFactory.getInstance(instance, (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }
}
