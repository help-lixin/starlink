package help.lixin.starlink.plugin.nacos.service;

import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceDetailInfo;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceForm;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceInfo;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServicePageList;
import help.lixin.response.PageResponse;

public interface INacosServiceManageService {

    PageResponse<NacosServiceDetailInfo> pageList(NacosServicePageList nacosServicePageList, String instanceName);

    Boolean create(NacosServiceForm nacosServiceForm,String instanceName);

    Boolean update(NacosServiceForm nacosServiceForm,String instanceName);

    Boolean remove(NacosServiceInfo nacosServiceInfo,String instanceName);

    String detail(NacosServiceInfo nacosServiceInfo, String instanceName);
}
