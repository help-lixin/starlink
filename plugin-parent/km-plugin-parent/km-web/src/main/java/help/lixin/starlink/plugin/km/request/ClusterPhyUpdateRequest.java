package help.lixin.starlink.plugin.km.request;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Min;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/16 12:23 下午
 * @Description
 */
@Api(tags = "更新集群详情请求对象")
public class ClusterPhyUpdateRequest extends ClusterPhyAddRequest {

    @Min(value = 0, message = "id不允许小于0")
    @ApiModelProperty(value="集群Id", example = "1")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
