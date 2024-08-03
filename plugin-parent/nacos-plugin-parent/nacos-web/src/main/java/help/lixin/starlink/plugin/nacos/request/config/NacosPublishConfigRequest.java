package help.lixin.starlink.plugin.nacos.request.config;

import javax.validation.Valid;

import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/14 6:48 下午
 * @Description
 */
@Api(tags = "发布配置请求对象")
public class NacosPublishConfigRequest {

    @Valid
    private EnvRequest envRequest;

    @ApiModelProperty(value = "配置名")
    private String dataId;

    @ApiModelProperty(value = "配置组名")
    private String nacosGroup;

    @ApiModelProperty(value = "租户id")
    private String tenant;

    @ApiModelProperty(value = "配置内容")
    private String content;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "配置名")
    private String appName;

    @ApiModelProperty(value = "配置标签列表，可多个，逗号分隔")
    private String configTags;

    @ApiModelProperty(value = "配置描述")
    private String desc;

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
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

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getConfigTags() {
        return configTags;
    }

    public void setConfigTags(String configTags) {
        this.configTags = configTags;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
