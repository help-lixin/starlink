package help.lixin.starlink.plugin.nacos.request.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/17 6:47 下午
 * @Description
 */
@Api(tags = "监听查询请求对象")
public class NacosListenConfigRequest {

    @ApiModelProperty(value = "配置名")
    private String dataId;

    @ApiModelProperty(value = "配置组名")
    private String group;

    @ApiModelProperty(value = "租户id")
    private String tenant;

    private Integer sampleTime;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public Integer getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(Integer sampleTime) {
        this.sampleTime = sampleTime;
    }
}
