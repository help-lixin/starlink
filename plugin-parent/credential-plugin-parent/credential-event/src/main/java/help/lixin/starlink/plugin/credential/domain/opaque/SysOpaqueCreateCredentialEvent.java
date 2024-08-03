package help.lixin.starlink.plugin.credential.domain.opaque;

import java.util.List;

import help.lixin.starlink.plugin.credential.event.opaque.ISysOpaqueCredentialEvent;
import help.lixin.starlink.plugin.credential.event.opaque.OpaqueCredentialEvent;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/24 下午7:36
 * @Description
 */
public class SysOpaqueCreateCredentialEvent implements ISysOpaqueCredentialEvent {

    private Long id;

    private List<OpaqueCredentialEvent> dataList;

    private String nameSpace;

    private String instanceCode;

    private String pluginCode;

    private String credentialKey;

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getPluginCode() {
        return pluginCode;
    }

    public void setPluginCode(String pluginCode) {
        this.pluginCode = pluginCode;
    }

    public String getCredentialKey() {
        return credentialKey;
    }

    public void setCredentialKey(String credentialKey) {
        this.credentialKey = credentialKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OpaqueCredentialEvent> getDataList() {
        return dataList;
    }

    public void setDataList(List<OpaqueCredentialEvent> dataList) {
        this.dataList = dataList;
    }
}
