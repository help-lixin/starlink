package help.lixin.starlink.core.dto;

import java.io.Serializable;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/12 6:09 下午
 * @Description
 */
public class EnvDTO implements Serializable {

    private String envCode;

    private String groupCode;

    private String instanceCode;

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getEnvCode() {
        return envCode;
    }

    public void setEnvCode(String envCode) {
        this.envCode = envCode;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }


    public String getInstanceName(){
        return envCode +":"+ groupCode +":"+ instanceCode;
    }
}
