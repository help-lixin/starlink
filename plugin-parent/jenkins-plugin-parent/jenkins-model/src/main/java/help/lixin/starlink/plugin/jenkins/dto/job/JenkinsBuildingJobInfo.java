package help.lixin.starlink.plugin.jenkins.dto.job;

/**
 * @Author: 伍岳林
 * @Date: 2024/3/13 下午8:29
 * @Description
 */
public class JenkinsBuildingJobInfo {

    private Long jobId;

    private String instanceCode;

    private String jobName;

    private Long buildId;

    private Integer jenkinsLogId;

    public Integer getJenkinsLogId() {
        return jenkinsLogId;
    }

    public void setJenkinsLogId(Integer jenkinsLogId) {
        this.jenkinsLogId = jenkinsLogId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getBuildId() {
        return buildId;
    }

    public void setBuildId(Long buildId) {
        this.buildId = buildId;
    }
}
