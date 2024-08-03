package help.lixin.starlink.plugin.nacos.request.config;

import java.util.List;

import javax.validation.Valid;

import help.lixin.starlink.plugin.nacos.api.model.config.NacosCloneItemConfig;
import help.lixin.starlink.plugin.nacos.api.model.enums.NacosImportEnum;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/17 6:45 下午
 * @Description
 */
@Api(tags = "克隆请求对象")
public class NacosCloneInfoConfigRequest {

    @Valid
    EnvRequest envRequest;

    private String srcUser;

    @ApiModelProperty(value = "租户id")
    private String tenant;

    @ApiModelProperty(value = "克隆配置列表")
    private List<NacosCloneItemConfig> configBeansList;

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

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public List<NacosCloneItemConfig> getConfigBeansList() {
        return configBeansList;
    }

    public void setConfigBeansList(List<NacosCloneItemConfig> configBeansList) {
        this.configBeansList = configBeansList;
    }

    public NacosImportEnum getPolicy() {
        return policy;
    }

    public void setPolicy(NacosImportEnum policy) {
        this.policy = policy;
    }
}
