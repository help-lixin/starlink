package help.lixin.starlink.plugin.k8s.request.namespace;

import help.lixin.starlink.request.PageRequest;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/5 下午5:18
 * @Description
 */
public class NameSpacePageListVO extends PageRequest {

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
