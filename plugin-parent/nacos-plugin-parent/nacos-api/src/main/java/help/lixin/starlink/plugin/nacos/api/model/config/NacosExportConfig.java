package help.lixin.starlink.plugin.nacos.api.model.config;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/17 6:36 下午
 * @Description
 */
public class NacosExportConfig {

    String dataId;
    String group;
    String appName;
    String tenant;
    List<Long> ids;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
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
