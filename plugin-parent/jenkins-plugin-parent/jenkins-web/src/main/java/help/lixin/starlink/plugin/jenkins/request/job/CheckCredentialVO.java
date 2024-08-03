package help.lixin.starlink.plugin.jenkins.request.job;

import javax.validation.constraints.NotBlank;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/29 10:33 上午
 * @Description
 */
public class CheckCredentialVO {

    @NotBlank(message = "实例编码不能为空")
    private String instanceCode;

    @NotBlank(message = "凭证id不能为空")
    private String credentialsId;

    @NotBlank(message = "校验路径不能为空")
    private String path;

    @NotBlank(message = "任务名不能为空")
    private String jobName;

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

    public String getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(String credentialsId) {
        this.credentialsId = credentialsId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
