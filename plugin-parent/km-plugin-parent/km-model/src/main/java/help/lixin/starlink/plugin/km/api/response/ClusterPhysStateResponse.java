package help.lixin.starlink.plugin.km.api.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "集群状态信息")
public class ClusterPhysStateResponse {
    @ApiModelProperty(value = "存活集群数", example = "30")
    private Integer liveCount;

    @ApiModelProperty(value = "挂掉集群数", example = "10")
    private Integer downCount;

    @ApiModelProperty(value = "未知状态集群数", example = "10")
    private Integer unknownCount;

    @ApiModelProperty(value = "集群总数", example = "40")
    private Integer total;

    public Integer getLiveCount() {
        return liveCount;
    }

    public void setLiveCount(Integer liveCount) {
        this.liveCount = liveCount;
    }

    public Integer getDownCount() {
        return downCount;
    }

    public void setDownCount(Integer downCount) {
        this.downCount = downCount;
    }

    public Integer getUnknownCount() {
        return unknownCount;
    }

    public void setUnknownCount(Integer unknownCount) {
        this.unknownCount = unknownCount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
