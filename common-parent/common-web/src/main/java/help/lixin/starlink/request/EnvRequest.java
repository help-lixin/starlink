package help.lixin.starlink.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/12 6:09 下午
 * @Description
 */
@Api(tags = "环境变量")
public class EnvRequest implements Serializable {

    @ApiModelProperty(value = "环境（dev/test/pro）配置")
    private String envCode;

    @ApiModelProperty(value = "组配置")
    private String groupCode;

    @NotNull(message = "实例code不能为空")
    @ApiModelProperty(value = "实例code")
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

    public String toInstanceName() {
        return envCode + ":" + groupCode + ":" + instanceCode;
    }
}
