package help.lixin.starlink.plugin.nacos.convert;

import help.lixin.starlink.plugin.nacos.api.model.config.NacosCreateConfig;
import help.lixin.starlink.plugin.nacos.api.model.config.NacosDetailConfig;
import help.lixin.starlink.plugin.nacos.api.model.config.NacosUpdateConfig;
import help.lixin.starlink.plugin.nacos.domain.NacosConfigCenter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface ConfigImplConvert {

    @Mapping(source = "tag",target = "configTags")
    @Mapping(source = "group",target = "groupName")
    @Mapping(source = "desc",target = "remark")
    NacosConfigCenter convert(NacosCreateConfig nacosCreateConfig);

    @Mapping(source = "tag",target = "configTags")
    @Mapping(source = "group",target = "groupName")
    @Mapping(source = "desc",target = "remark")
    NacosConfigCenter convert(NacosUpdateConfig nacosUpdateConfig);

    @Mapping(source = "instancSpaceId",target = "nameSpaceId")
    NacosDetailConfig convertDetail(NacosCreateConfig nacosCreateConfig);
}
