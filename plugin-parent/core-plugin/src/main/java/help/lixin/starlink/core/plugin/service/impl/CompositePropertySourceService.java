package help.lixin.starlink.core.plugin.service.impl;

import help.lixin.starlink.core.dto.PropertySource;
import help.lixin.starlink.core.plugin.service.IPropertySourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 组合配置拉取
 */
public class CompositePropertySourceService implements IPropertySourceService {

    protected Logger logger = LoggerFactory.getLogger(CompositePropertySourceService.class);

    private List<IPropertySourceService> propertySourceServiceList;

    public CompositePropertySourceService(List<IPropertySourceService> propertySourceServiceList) {
        this.propertySourceServiceList = propertySourceServiceList;
    }

    @Override
    public List<PropertySource> pull(String pluginCode, String instanceCode) {
        List<PropertySource> resultList = new ArrayList<>(0);
        for (IPropertySourceService propertySourceService : propertySourceServiceList) {
            try {
                List<PropertySource> propertySources = propertySourceService.pull(pluginCode, instanceCode);
                // 根据:pluginCode,有找到配置文件的话,不再继续往下找配置文件了的.
                if (null != propertySources && !propertySources.isEmpty()) {
                    resultList.addAll(propertySources);
                    break;
                }
            } catch (Exception e) {
                logger.warn("读取配置信息出现异常,异常详细信息如下:[\n{}\n]", e);
            }
        }
        return resultList;
    }
}
