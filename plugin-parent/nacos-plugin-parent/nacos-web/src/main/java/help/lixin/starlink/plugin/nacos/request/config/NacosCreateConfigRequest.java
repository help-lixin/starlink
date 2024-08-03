package help.lixin.starlink.plugin.nacos.request.config;

import javax.validation.Valid;

import help.lixin.starlink.plugin.nacos.api.model.enums.NacosTypeEnum;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/13 3:15 下午
 * @Description
 */
@Api(tags = "新增配置请求对象")
public class NacosCreateConfigRequest {

    @Valid
    private EnvRequest envRequest;

    // 租户id
    @ApiModelProperty(value = "租户id")
    private String tenant;
    // 实例空间id
    @ApiModelProperty(value = "实例空间id")
    private String instancSpaceId;
    // 配置名
    @ApiModelProperty(value = "配置名")
    private String dataId;
    // 配置组名
    @ApiModelProperty(value = "配置组名")
    private String nacosGroup;
    // 配置信息
    @ApiModelProperty(value = "配置信息")
    private String content;
    // 描述
    @ApiModelProperty(value = "描述")
    private String desc;
    // 标签
    @ApiModelProperty(value = "标签")
    private String tag;
    // 格式类型
    @ApiModelProperty(value = "格式类型(" + "TEXT\n" + "JSON\n" + "XML\n" + "YAML\n" + "HTML\n" + "Properties)")
    private NacosTypeEnum type = NacosTypeEnum.TEXT;
    // 归属应用
    @ApiModelProperty(value = "归属应用")
    private String appName;

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

    public String getInstancSpaceId() {
        return instancSpaceId;
    }

    public void setInstancSpaceId(String instancSpaceId) {
        this.instancSpaceId = instancSpaceId;
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
