package help.lixin.starlink.plugin.nacos.api.model.config;

import help.lixin.starlink.plugin.nacos.api.model.enums.NacosImportEnum;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/17 6:45 下午
 * @Description
 */
public class NacosCloneInfoConfig {
    String srcUser;
    String tenant;
    List<NacosCloneItemConfig> configBeansList;
    NacosImportEnum policy;

    public String getSrcUser() {
        return srcUser;
    }

    public void setSrcUser(String srcUser) {
        this.srcUser = srcUser;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public List<NacosCloneItemConfig> getConfigBeansList() {
        return configBeansList;
    }

    public void setConfigBeansList(List<NacosCloneItemConfig> configBeansList) {
        this.configBeansList = configBeansList;
    }

    public NacosImportEnum getPolicy() {
        return policy;
    }

    public void setPolicy(NacosImportEnum policy) {
        this.policy = policy;
    }
}
