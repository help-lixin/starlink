package help.lixin.starlink.plugin.xxl.job.request.log;

import javax.validation.Valid;

import help.lixin.starlink.request.EnvRequest;
import help.lixin.starlink.request.PageRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/7 3:24 下午
 * @Description
 */
@Api(tags = "调度日志分页查询对象")
public class JobLogPageListVO {

    @Valid
    private PageRequest pageRequest;

    @Valid
    private EnvRequest envRequest;

    @ApiModelProperty(value = "执行器id")
    private Integer jobGroup;

    @ApiModelProperty(value = "任务id")
    private Integer jobId;

    @ApiModelProperty(value = "日志状态")
    private Integer logStatus;

    private String filterTime;

    public PageRequest getPageRequest() {
        return pageRequest;
    }

    public void setPageRequest(PageRequest pageRequest) {
        this.pageRequest = pageRequest;
    }

    public Integer getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(Integer jobGroup) {
        this.jobGroup = jobGroup;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getLogStatus() {
        return logStatus;
    }

    public void setLogStatus(Integer logStatus) {
        this.logStatus = logStatus;
    }

    public String getFilterTime() {
        return filterTime;
    }

    public void setFilterTime(String filterTime) {
        this.filterTime = filterTime;
    }

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }
}
