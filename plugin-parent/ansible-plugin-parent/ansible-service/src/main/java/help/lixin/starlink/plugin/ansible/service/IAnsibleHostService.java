package help.lixin.starlink.plugin.ansible.service;

import help.lixin.starlink.plugin.ansible.domain.AnsibleHostManage;
import help.lixin.starlink.plugin.ansible.dto.*;
import help.lixin.response.PageResponse;

public interface IAnsibleHostService {

    PageResponse<AnsibleHostManage> pageList(HostPageListDTO hostPageListDTO);

    Boolean saveHost(CreateHostDTO createHostDTO);

    Boolean changeStatus(Long id, Integer status, String createBy);

    Boolean checkServerName(String name, String serverName);

    AnsibleHostManage queryDetail(Long id);

    Boolean checkInstance(String sshInstanceCode);

    Boolean removeHost(Long id);
}
