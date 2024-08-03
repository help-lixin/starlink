package help.lixin.starlink.plugin.eureka.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/31 10:04 上午
 * @Description
 */
@Api(tags = "实例请求对象")
public class InstanceRequest {

    @NotBlank(message = "应用名称不能为空")
    @ApiModelProperty(value = "应用名称")
    private String appName;

    @NotBlank(message = "实例id不能为空")
    @ApiModelProperty(value = "实例id")
    private String instanceId;

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
