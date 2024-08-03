package help.lixin.starlink.plugin.k8s.request.base;

import help.lixin.starlink.request.PageRequest;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/5 下午5:18
 * @Description
 */
public class BasePageListVO extends PageRequest {

    protected String instanceCode;
    protected String nameSpace;
    protected Long nameSpaceId;
    protected String kind;
    protected String name;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

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

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }
}
