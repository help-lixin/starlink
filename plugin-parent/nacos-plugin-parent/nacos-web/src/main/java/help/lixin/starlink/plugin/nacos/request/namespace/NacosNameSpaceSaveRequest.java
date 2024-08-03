package help.lixin.starlink.plugin.nacos.request.namespace;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/20 5:41 下午
 * @Description
 */
@Api(tags = "命名空间请求对象")
public class NacosNameSpaceSaveRequest {

    @Valid
    private EnvRequest envRequest;

    private String nameSpaceId;

    @NotBlank(message = "命名空间名称不能为空")
    @ApiModelProperty(value = "命名空间名称")
    private String nameSpaceName;

    @NotBlank(message = "命名空间描述不能为空")
    @ApiModelProperty(value = "命名空间描述")
    private String nameSpaceDesc;

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }

    public String getNameSpaceId() {
        return nameSpaceId;
    }

    public void setNameSpaceId(String nameSpaceId) {
        this.nameSpaceId = nameSpaceId;
    }

    public String getNameSpaceName() {
        return nameSpaceName;
    }

    public void setNameSpaceName(String nameSpaceName) {
        this.nameSpaceName = nameSpaceName;
    }

    public String getNameSpaceDesc() {
        return nameSpaceDesc;
    }

    public void setNameSpaceDesc(String nameSpaceDesc) {
        this.nameSpaceDesc = nameSpaceDesc;
    }
}
