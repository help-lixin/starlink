package help.lixin.starlink.plugin.jenkins.dto.job;

import help.lixin.starlink.core.dto.PageDTO;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/21 5:49 下午
 * @Description
 */
public class JenkinsJobPageListDTO extends PageDTO {

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
