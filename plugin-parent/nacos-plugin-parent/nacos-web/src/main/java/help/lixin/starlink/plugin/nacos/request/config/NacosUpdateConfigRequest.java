package help.lixin.starlink.plugin.nacos.request.config;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import help.lixin.starlink.plugin.nacos.api.model.enums.NacosTypeEnum;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/11 4:33 下午
 * @Description
 */
@Api(tags = "更新配置请求对象")
public class NacosUpdateConfigRequest {

    @Valid
    private EnvRequest envRequest;

    @NotNull(message = "id不能为空")
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "租户id")
    private String tenant;

    @ApiModelProperty(value = "命名空间id")
    private String nameSpaceId;

    @NotBlank(message = "配置名不能为空")
    @ApiModelProperty(value = "配置名")
    private String dataId;

    @NotBlank(message = "配置组名不能为空")
    @ApiModelProperty(value = "配置组名")
    private String nacosGroup;

    @NotBlank(message = "配置内容不能为空")
    @ApiModelProperty(value = "配置内容")
    private String content;

    // 描述
    @ApiModelProperty(value = "描述")
    private String desc;

    // 标签
    @ApiModelProperty(value = "标签")
    private String tag;

    // 格式类型
    @ApiModelProperty(value = "配置类型")
    private NacosTypeEnum type;

    // 归属应用
    @ApiModelProperty(value = "归属应用")
    private String appName;

    // Beta发布
    @ApiModelProperty(value = "Beta发布")
    private String betaIps;

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getNameSpaceId() {
        return nameSpaceId;
    }

    public void setNameSpaceId(String nameSpaceId) {
        this.nameSpaceId = nameSpaceId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBetaIps() {
        return betaIps;
    }

    public void setBetaIps(String betaIps) {
        this.betaIps = betaIps;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getNacosGroup() {
        return nacosGroup;
    }

    public void setNacosGroup(String nacosGroup) {
        this.nacosGroup = nacosGroup;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public NacosTypeEnum getType() {
        return type;
    }

    public void setType(NacosTypeEnum type) {
        this.type = type;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}
