package help.lixin.starlink.plugin.nacos.request.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/11 4:33 下午
 * @Description
 */
@Api(tags = "查询版本历史详情请求对象")
public class NacosDetailHistoryConfigRequest {

    @NotNull(message = "id不能为空")
    private Long id;

    @NotBlank(message = "命名空间id不能为空")
    @ApiModelProperty(value = "命名空间id")
    private String nameSpaceId;

    @NotBlank(message = "租户id不能为空")
    @ApiModelProperty(value = "租户id")
    private String tenant;

    @NotBlank(message = "配置名不能为空")
    @ApiModelProperty(value = "配置名")
    private String dataId;

    @NotBlank(message = "配置组名不能为空")
    @ApiModelProperty(value = "配置组名")
    private String nacosGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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




}
