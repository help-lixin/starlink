package help.lixin.starlink.plugin.xxl.job.dto.log;

import help.lixin.starlink.request.PageRequest;
import io.swagger.annotations.Api;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/7 3:24 下午
 * @Description
 */
@Api(tags = "调度日志分页查询对象")
public class JobLogPageListDTO {

    private String instanceName;
    private PageRequest pageRequest;
    private Integer jobGroup;
    private Integer jobId;
    private Integer logStatus;
    private String filterTime;

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

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

}
