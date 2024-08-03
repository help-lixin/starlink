package help.lixin.starlink.core.plugin.service;

import help.lixin.starlink.core.dto.PropertySource;

import java.util.List;

public interface IPropertySourceService {
    /**
     * 根据插件code拉取配置文件信息列表
     *
     * @param pluginCode
     * @return
     */
    List<PropertySource> pull(String pluginCode, String instanceCode);
}
