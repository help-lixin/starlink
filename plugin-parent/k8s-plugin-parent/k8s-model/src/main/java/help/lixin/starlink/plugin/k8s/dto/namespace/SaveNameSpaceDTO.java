package help.lixin.starlink.plugin.k8s.dto.namespace;

/**
 * @Author: 伍岳林
 * @Date: 2024/7/9 下午4:54
 * @Description
 */
public class SaveNameSpaceDTO {

    private String instanceCode;

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
