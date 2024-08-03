package help.lixin.starlink.plugin.xxl.job.request.log;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/7 4:10 下午
 * @Description
 */
@Api(tags = "清理日志请求对象")
public class JobLogClearVO {

    @Valid
    private EnvRequest envRequest;

    @NotNull(message = "执行器id不能为空")
    @ApiModelProperty(value = "执行器id")
    private Integer jobGroupId;

    @NotNull(message = "任务id不能为空")
    @ApiModelProperty(value = "任务id")
    private Integer jobId;

    @NotNull(message = "清理方式不能为空")
    @ApiModelProperty(value = "清理方式")
    private Integer type;

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
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
