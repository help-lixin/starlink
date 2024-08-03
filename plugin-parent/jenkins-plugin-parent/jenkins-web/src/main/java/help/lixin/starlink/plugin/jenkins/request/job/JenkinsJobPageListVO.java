package help.lixin.starlink.plugin.jenkins.request.job;

import help.lixin.starlink.request.PageRequest;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/21 10:15 上午
 * @Description
 */
public class JenkinsJobPageListVO extends PageRequest {

    private String jobName;

    private String tools;

    private String instanceCode;

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

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
}
