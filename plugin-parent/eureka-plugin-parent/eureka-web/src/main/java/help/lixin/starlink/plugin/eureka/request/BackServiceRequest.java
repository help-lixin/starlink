package help.lixin.starlink.plugin.eureka.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/31 10:36 上午
 * @Description
 */
public class BackServiceRequest {

    @NotBlank(message = "应用名称不能为空")
    @ApiModelProperty(value = "应用名称")
    private String appName;

    @NotBlank(message = "实例id不能为空")
    @ApiModelProperty(value = "实例id")
    private String instanceId;

    @ApiModelProperty(value = "实例状态")
    private InstanceStatus status;

    @Valid
    private EnvRequest envRequest;

    public InstanceStatus getStatus() {
        return status;
    }

    public void setStatus(InstanceStatus status) {
        this.status = status;
    }

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }
}
