package help.lixin.starlink.plugin.nacos.api.service;

import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceDetailInfo;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServicePageList;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceForm;
import help.lixin.starlink.plugin.nacos.api.model.servicemanage.NacosServiceInfo;
import help.lixin.response.PageResponse;

public interface INacosServiceManageService {

    PageResponse<NacosServiceDetailInfo> pageList(NacosServicePageList nacosServicePageList);

    Boolean create(NacosServiceForm nacosServiceForm);

    Boolean update(NacosServiceForm nacosServiceForm);

    Boolean remove(NacosServiceInfo nacosServiceInfo);

    String detail(NacosServiceInfo nacosServiceInfo);
}
