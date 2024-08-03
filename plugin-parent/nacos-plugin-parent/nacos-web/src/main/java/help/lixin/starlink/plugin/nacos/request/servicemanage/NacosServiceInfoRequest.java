package help.lixin.starlink.plugin.nacos.request.servicemanage;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/21 7:40 下午
 * @Description
 */
public class NacosServiceInfoRequest {

    String namespaceId;

    @NotBlank(message = "服务名称")
    String serviceName;

    @NotBlank(message = "组名称")
    String groupName;

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
