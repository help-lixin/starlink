package help.lixin.starlink.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/7 5:27 下午
 * @Description
 */
public class BasePageRequest implements Serializable {

    @ApiModelProperty(value = "当前页,默认:1")
    protected Integer pageNum;

    @ApiModelProperty(value = "每页显示条数,默认:10")
    protected Integer pageSize;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum == null || pageNum < 0 ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize == null || pageSize < 10 ? 10 : pageSize;
    }
}
