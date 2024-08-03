package help.lixin.starlink.plugin.xxl.job.dto.log;

import io.swagger.annotations.Api;

@Api(tags = "查看详情请求对象")
public class JobLogDetailDTO {

    private String instanceName;
    private String executorAddress;
    private Long triggerTime;
    private Long logId;
    private Integer fromLineNum;

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
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
