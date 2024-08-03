package help.lixin.starlink.plugin.k8s.dto.base;

import help.lixin.starlink.core.dto.PageDTO;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/5 下午5:18
 * @Description
 */
public class NameSpacePageListDTO extends PageDTO {

    private String instanceCode;
    private String name;

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

}
