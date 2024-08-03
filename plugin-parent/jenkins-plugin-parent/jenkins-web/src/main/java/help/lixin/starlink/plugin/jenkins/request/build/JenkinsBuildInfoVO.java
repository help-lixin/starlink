package help.lixin.starlink.plugin.jenkins.request.build;

import java.io.Serializable;

public class JenkinsBuildInfoVO implements Serializable {
    private Long id;

    private Long jobId;

    private Long jenkinsLogId;

    private Integer buildStatus;

    private Integer status;

    private String createBy;

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

    public Long getJenkinsLogId() {
        return jenkinsLogId;
    }

    public void setJenkinsLogId(Long jenkinsLogId) {
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