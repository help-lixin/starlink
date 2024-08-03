package help.lixin.starlink.plugin.nacos.api.model.config;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/12 2:53 下午
 * @Description
 */
public class NacosDeleteConfig {

    private String namespaceId;

    private String tenant;

    private List<Long> ids;

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
