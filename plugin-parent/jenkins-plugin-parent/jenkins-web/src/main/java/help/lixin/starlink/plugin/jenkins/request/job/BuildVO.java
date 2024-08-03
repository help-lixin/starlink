package help.lixin.starlink.plugin.jenkins.request.job;

/**
 * @Param null :
 * @Author: 伍岳林
 * @Date: 2023/12/29 4:55 下午
 * @Return:
 * @Description
*/
public class BuildVO {

    String instanceCode;

    Long jobId;


    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
}
