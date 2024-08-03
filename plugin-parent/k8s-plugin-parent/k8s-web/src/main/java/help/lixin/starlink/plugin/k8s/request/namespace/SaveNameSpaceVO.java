package help.lixin.starlink.plugin.k8s.request.namespace;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 伍岳林
 * @Date: 2024/7/9 下午4:54
 * @Description
 */
public class SaveNameSpaceVO {

    @NotBlank(message = "实例编码不能为空")
    private String instanceCode;

    @NotBlank(message = "命名空间名称不能为空")
    private String name;

    private String remark;

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
