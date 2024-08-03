package help.lixin.starlink.plugin.ansible.dto;

import help.lixin.starlink.core.dto.PageDTO;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/29 上午11:03
 * @Description
 */
public class HostPageListDTO extends PageDTO {

    private String serverName;

    private String sshInstanceCode;

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }

    public String getSshInstanceCode() {
        return sshInstanceCode;
    }

    public void setSshInstanceCode(String sshInstanceCode) {
        this.sshInstanceCode = sshInstanceCode;
    }
}
