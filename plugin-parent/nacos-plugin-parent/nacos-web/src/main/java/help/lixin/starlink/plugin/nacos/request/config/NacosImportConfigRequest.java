package help.lixin.starlink.plugin.nacos.request.config;

import javax.validation.Valid;

import help.lixin.starlink.plugin.nacos.api.model.enums.NacosImportEnum;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/17 6:37 下午
 * @Description
 */
@Api(tags = "导入配置请求对象")
public class NacosImportConfigRequest {

    @Valid
    private EnvRequest envRequest;

    private String srcUser;

    @ApiModelProperty(value = "命名空间id")
    private String nameSpaceId;

    @ApiModelProperty(value = "策略:ABORT(终止导入),SKIP(跳过),OVERWRITE(覆盖)")
    private NacosImportEnum policy = NacosImportEnum.ABORT;

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }

    public String getSrcUser() {
        return srcUser;
    }

    public void setSrcUser(String srcUser) {
        this.srcUser = srcUser;
    }

    public String getNameSpaceId() {
        return nameSpaceId;
    }

    public void setNameSpaceId(String nameSpaceId) {
        this.nameSpaceId = nameSpaceId;
    }

    public NacosImportEnum getPolicy() {
        return policy;
    }

    public void setPolicy(NacosImportEnum policy) {
        this.policy = policy;
    }
}
