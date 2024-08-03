package help.lixin.starlink.plugin.credential.domain.namespace;

import java.util.Date;

public class NameSpaceCreate {

    /**
     * 插件实例
     */
    private String instanceCode;

    /**
     * 命名空间
     */
    private String nameSpace;

    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode == null ? null : instanceCode.trim();
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace == null ? null : nameSpace.trim();
    }

}