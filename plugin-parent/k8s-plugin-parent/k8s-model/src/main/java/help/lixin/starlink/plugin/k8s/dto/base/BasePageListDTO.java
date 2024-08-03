package help.lixin.starlink.plugin.k8s.dto.base;

import help.lixin.starlink.core.dto.PageDTO;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/5 下午5:18
 * @Description
 */
public class BasePageListDTO extends PageDTO {

    private String instanceCode;
    private Long nameSpaceId;
    private String nameSpace;
    private String name;
    private String kind;

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
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

    public Long getNameSpaceId() {
        return nameSpaceId;
    }

    public void setNameSpaceId(Long nameSpaceId) {
        this.nameSpaceId = nameSpaceId;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }
}
