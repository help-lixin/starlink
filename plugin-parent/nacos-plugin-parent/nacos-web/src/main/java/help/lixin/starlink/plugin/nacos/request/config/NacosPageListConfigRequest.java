package help.lixin.starlink.plugin.nacos.request.config;

import javax.validation.Valid;

import help.lixin.starlink.plugin.nacos.api.model.enums.NacosSearchEnum;
import help.lixin.starlink.request.EnvRequest;
import help.lixin.starlink.request.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/11 4:33 下午
 * @Description
 */
@Api(tags = "分页配置请求对象")
public class NacosPageListConfigRequest extends PageRequest {

    @Valid
    private EnvRequest envRequest;

    @ApiModelProperty(value = "租户id")
    private String tenant;

    @ApiModelProperty(value = "配置名")
    private String dataId;

    @ApiModelProperty(value = "配置组名")
    private String nacosGroup;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "归属应用")
    private String appName;

    @ApiModelProperty(value = "查询方式（ACCURATE\n" + "BLUR）")
    private NacosSearchEnum search;

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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public NacosSearchEnum getSearch() {
        return search == null ? NacosSearchEnum.BLUR : search;
    }

    public void setSearch(NacosSearchEnum search) {
        this.search = search;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

}
