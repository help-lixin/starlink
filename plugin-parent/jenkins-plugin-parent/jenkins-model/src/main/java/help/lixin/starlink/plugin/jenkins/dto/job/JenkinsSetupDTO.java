package help.lixin.starlink.plugin.jenkins.dto.job;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/21 10:42 上午
 * @Description
 */
public class JenkinsSetupDTO {

    private Long jobId;

    private String setupType;


    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getSetupType() {
        return setupType;
    }

    public void setSetupType(String setupType) {
        this.setupType = setupType;
    }
}
