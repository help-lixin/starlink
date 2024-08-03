package help.lixin.starlink.plugin.xxl.job.dto.log;

import io.swagger.annotations.Api;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/7 4:10 下午
 * @Description
 */
@Api(tags = "清理日志请求对象")
public class JobLogClearDTO {

    private String instanceName;

    private Integer jobGroupId;

    private Integer jobId;

    private Integer type;

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public Integer getJobGroupId() {
        return jobGroupId;
    }

    public void setJobGroupId(Integer jobGroupId) {
        this.jobGroupId = jobGroupId;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
