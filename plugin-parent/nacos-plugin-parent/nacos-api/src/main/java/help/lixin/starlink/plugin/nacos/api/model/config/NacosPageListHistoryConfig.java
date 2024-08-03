package help.lixin.starlink.plugin.nacos.api.model.config;

import help.lixin.starlink.core.dto.PageDTO;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/17 10:25 上午
 * @Description
 */
public class NacosPageListHistoryConfig extends PageDTO {
    private String dataId;
    private String group;
    private String namespaceId;

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

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }
}
