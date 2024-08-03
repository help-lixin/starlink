package help.lixin.starlink.plugin.nacos.convert.servicemanage;

import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceForm;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceInfo;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServicePageList;
import help.lixin.starlink.plugin.nacos.request.servicemanage.NacosServiceFormRequest;
import help.lixin.starlink.plugin.nacos.request.servicemanage.NacosServiceInfoRequest;
import help.lixin.starlink.plugin.nacos.request.servicemanage.NacosServicePageListRequest;
import org.mapstruct.Mapper;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/20 7:20 下午
 * @Description
 */
@Mapper
public interface ServiceManageConvert {


    NacosServicePageList convert(NacosServicePageListRequest nacosServicePageListRequest);

    NacosServiceForm convert(NacosServiceFormRequest nacosServiceFormRequest);

    NacosServiceInfo convert(NacosServiceInfoRequest nacosServiceInfoRequest);
}
