package help.lixin.starlink.plugin.xxl.job.request.log;

import javax.validation.Valid;

import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@Api(tags = "查看详情请求对象")
public class JobLogDetailVO {

    @Valid
    private EnvRequest envRequest;

    @ApiModelProperty(value = "执行器地址")
    private String executorAddress;

    @ApiModelProperty(value = "触发时间（时间戳）")
    private Long triggerTime;

    @ApiModelProperty(value = "日志id")
    private Long logId;

    private Integer fromLineNum;

    public EnvRequest getEnvRequest() {
        return envRequest;
    }

    public void setEnvRequest(EnvRequest envRequest) {
        this.envRequest = envRequest;
    }

    public String getExecutorAddress() {
        return executorAddress;
    }

    public void setExecutorAddress(String executorAddress) {
        this.executorAddress = executorAddress;
    }

    public Long getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Long triggerTime) {
        this.triggerTime = triggerTime;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Integer getFromLineNum() {
        return fromLineNum;
    }

    public void setFromLineNum(Integer fromLineNum) {
        this.fromLineNum = fromLineNum;
    }
}
