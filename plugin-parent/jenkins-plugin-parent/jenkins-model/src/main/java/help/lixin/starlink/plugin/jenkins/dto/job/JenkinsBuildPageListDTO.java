package help.lixin.starlink.plugin.jenkins.dto.job;

import help.lixin.starlink.core.dto.PageDTO;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/21 5:49 下午
 * @Description
 */
public class JenkinsBuildPageListDTO extends PageDTO {

    private String jobName;

    private String instanceCode;

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
