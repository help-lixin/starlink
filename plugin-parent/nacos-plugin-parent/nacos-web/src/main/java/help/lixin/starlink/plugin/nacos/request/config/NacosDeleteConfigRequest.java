package help.lixin.starlink.plugin.nacos.request.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/12 2:53 下午
 * @Description
 */
@Api(tags = "删除配置请求对象")
public class NacosDeleteConfigRequest {

    @ApiModelProperty(value = "命名空间id")
    private String namespaceId;

    @ApiModelProperty(value = "租户id")
    private String tenant;

    @NotBlank(message = "ids不能为空")
    @ApiModelProperty(value = "删除id,选择批量删除用逗号隔开")
    private List<Long> ids;

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
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
