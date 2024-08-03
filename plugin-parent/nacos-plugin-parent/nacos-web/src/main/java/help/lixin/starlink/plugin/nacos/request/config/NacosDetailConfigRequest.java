package help.lixin.starlink.plugin.nacos.request.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/11 4:33 下午
 * @Description
 */
@Api(tags = "查询配置详情对象")
public class NacosDetailConfigRequest {

    @NotBlank(message = "命名空间id不能为空")
    @ApiModelProperty(value = "命名空间id")
    private String nameSpaceId;

    @NotBlank(message = "命名空间id不能为空")
    @ApiModelProperty(value = "命名空间id")
    private String tenant;

    @NotBlank(message = "命名空间id不能为空")
    @ApiModelProperty(value = "命名空间id")
    private String dataId;

    @NotBlank(message = "配置组名")
    @ApiModelProperty(value = "配置组名")
    private String nacosGroup;
    //标签
    private String tag;


    public String getNameSpaceId() {
        return nameSpaceId;
    }

    public void setNameSpaceId(String nameSpaceId) {
        this.nameSpaceId = nameSpaceId;
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


}
