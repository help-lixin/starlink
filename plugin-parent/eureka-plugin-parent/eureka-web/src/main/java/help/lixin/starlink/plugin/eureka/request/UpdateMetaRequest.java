package help.lixin.starlink.plugin.eureka.request;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/31 10:47 上午
 * @Description
 */
@Api(tags = "更新元数据请求对象")
public class UpdateMetaRequest {

    @NotBlank(message = "应用名称不能为空")
    @ApiModelProperty(value = "应用名称")
    private String appName;

    @NotBlank(message = "实例id不能为空")
    @ApiModelProperty(value = "实例id")
    private String instanceId;

    @NotBlank(message = "元数据key不能为空")
    @ApiModelProperty(value = "元数据key")
    private String key;

    @NotBlank(message = "元数据value不能为空")
    @ApiModelProperty(value = "元数据value")
    private String value;

    @Valid
    private EnvRequest envRequest;

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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }
}
