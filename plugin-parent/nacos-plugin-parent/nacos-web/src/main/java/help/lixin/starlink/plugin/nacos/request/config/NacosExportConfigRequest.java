package help.lixin.starlink.plugin.nacos.request.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/17 6:36 下午
 * @Description
 */
@Api(tags = "导出配置请求对象")
public class NacosExportConfigRequest {

    @ApiModelProperty(value = "配置名")
    private String dataId;

    @ApiModelProperty(value = "配置组名")
    private String nacosGroup;

    @ApiModelProperty(value = "归属应用")
    private String appName;

    @ApiModelProperty(value = "租户id")
    private String tenant;

    private @ApiModelProperty(value = "id列表")
    List<Long> ids;

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

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }
}
