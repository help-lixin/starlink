package help.lixin.starlink.plugin.k8s.request.base;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/17 下午5:46
 * @Description
 */
public abstract class BaseInfoVO {
    @NotBlank(message = "实例编码不能为空")
    protected String instanceCode;
    @NotBlank(message = "命名空间不能为空")
    protected String namespace;
    @NotBlank(message = "命名空间id不能为空")
    protected Long nameSpaceId;
    @NotBlank(message = "名称不能为空")
    protected String name;

    public Long getNameSpaceId() {
        return nameSpaceId;
    }

    public void setNameSpaceId(Long nameSpaceId) {
        this.nameSpaceId = nameSpaceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

}
