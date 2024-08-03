package help.lixin.starlink.plugin.nacos.convert.namespace;

import help.lixin.starlink.plugin.nacos.api.model.namespace.NacosNameSpaceSave;
import help.lixin.starlink.plugin.nacos.request.namespace.NacosNameSpaceSaveRequest;
import org.mapstruct.Mapper;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/20 7:20 下午
 * @Description
 */
@Mapper
public interface NameSpaceConvert {

    NacosNameSpaceSave convert(NacosNameSpaceSaveRequest nacosNameSpaceSaveRequest);
}
