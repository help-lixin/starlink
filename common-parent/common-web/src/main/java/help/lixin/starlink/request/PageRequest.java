package help.lixin.starlink.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/7 5:27 下午
 * @Description
 */
public class PageRequest extends BasePageRequest implements Serializable {

    @ApiModelProperty(value = "状态")
    protected Integer status;

    @ApiModelProperty(value = "开始时间")
    protected String beginTime;

    @ApiModelProperty(value = "结束时间")
    protected String endTime;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

}
