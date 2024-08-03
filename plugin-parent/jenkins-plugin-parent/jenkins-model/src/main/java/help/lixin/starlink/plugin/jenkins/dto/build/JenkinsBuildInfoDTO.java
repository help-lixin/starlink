package help.lixin.starlink.plugin.jenkins.dto.build;

import java.io.Serializable;

public class JenkinsBuildInfoDTO implements Serializable {
    private Long id;

    private Long jobId;

    private String instanceCode;

    private String jobName;

    private Integer jenkinsLogId;

    private Integer buildStatus;

    private Integer status;

    private String createBy;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Integer getJenkinsLogId() {
        return jenkinsLogId;
    }

    public void setJenkinsLogId(Integer jenkinsLogId) {
        this.jenkinsLogId = jenkinsLogId;
    }

    public Integer getBuildStatus() {
        return buildStatus;
    }

    public void setBuildStatus(Integer buildStatus) {
        this.buildStatus = buildStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}