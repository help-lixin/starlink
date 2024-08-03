package help.lixin.starlink.plugin.nacos.convert.config;

import help.lixin.starlink.plugin.nacos.api.model.config.*;
import help.lixin.starlink.plugin.nacos.request.config.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/12 5:35 下午
 * @Description
 */
@Mapper
public interface ConfigConvert {

    @Mapping(target = "group",source = "nacosGroup")
    NacosDetailConfig convert(NacosDetailConfigRequest nacosDetailConfigRequest);

    @Mapping(target = "group",source = "nacosGroup")
    NacosCreateConfig convert(NacosCreateConfigRequest nacosDetailConfigRequest);

    @Mapping(target = "group",source = "nacosGroup")
    NacosUpdateConfig convert(NacosUpdateConfigRequest nacosUpdateConfigRequest);

    NacosDeleteConfig convert(NacosDeleteConfigRequest nacosDeleteConfigRequest);

    @Mapping(target = "group",source = "nacosGroup")
    @Mapping(target = "envDTO",source = "envRequest")
    NacosPageListConfig convert(NacosPageListConfigRequest nacosPageListConfigRequest);

    @Mapping(target = "group",source = "nacosGroup")
    NacosPublishConfig convert(NacosPublishConfigRequest nacosPublishConfigRequest);

    @Mapping(target = "group",source = "nacosGroup")
    NacosPageListHistoryConfig convert(NacosPageListHistoryConfigRequest nacosPageListHistoryConfigRequest);

    @Mapping(target = "group",source = "nacosGroup")
    NacosDetailHistoryConfig convert(NacosDetailHistoryConfigRequest nacosDetailHistoryConfigRequest);

    @Mapping(target = "group",source = "nacosGroup")
    NacosExportConfig convert(NacosExportConfigRequest nacosExportConfigRequest);

    NacosImportConfig convert(NacosImportConfigRequest nacosImportConfigRequest);

    NacosCloneInfoConfig convert(NacosCloneInfoConfigRequest nacosCloneInfoConfigRequest);

    NacosListenConfig convert(NacosListenConfigRequest nacosListenConfigRequest);
}
