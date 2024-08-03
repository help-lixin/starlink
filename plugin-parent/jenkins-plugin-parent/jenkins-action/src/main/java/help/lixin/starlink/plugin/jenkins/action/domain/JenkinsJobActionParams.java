package help.lixin.starlink.plugin.jenkins.action.domain;

import help.lixin.starlink.plugin.jenkins.action.domain.scm.JenkinsPipelineScm;
import help.lixin.starlink.plugin.jenkins.action.domain.setups.JenkinsPipelineSetupCommon;
import help.lixin.starlink.plugin.jenkins.domain.ProjectType;
import help.lixin.starlink.plugin.jenkins.domain.ScmType;
import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

import java.util.ArrayList;
import java.util.List;

public class JenkinsJobActionParams {

    private String instanceCode;

    private String jobName;

    private ProjectType projectType = ProjectType.FREE_STYLE_PROJECT;

    /**
     * 仓库类型(svn/git),主要是方便前端进行切换
     */
    private ScmType scmType;

    /**
     * 工具类型(maven/ant/gradle/python/go...)
     */
    private ToolsType toolsType;

    /**
     * 对应(jenkins_system_config)表里的id
     */
    private String jdkId;

    /**
     * 仓库详细信息配置
     */
    private JenkinsPipelineScm scm;

    private List<JenkinsPipelineSetupCommon> setups = new ArrayList<>(0);

    // 执行最大等待时间(以分钟为单位)
    private long executeMaxWaitTime = 60;

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

    public ProjectType getProjectType() {
        return projectType;
    }

    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public ScmType getScmType() {
        return scmType;
    }

    public void setScmType(ScmType scmType) {
        this.scmType = scmType;
    }

    public ToolsType getToolsType() {
        return toolsType;
    }

    public void setToolsType(ToolsType toolsType) {
        this.toolsType = toolsType;
    }

    public String getJdkId() {
        return jdkId;
    }

    public void setJdkId(String jdkId) {
        this.jdkId = jdkId;
    }

    public JenkinsPipelineScm getScm() {
        return scm;
    }

    public void setScm(JenkinsPipelineScm scm) {
        this.scm = scm;
    }

    public List<JenkinsPipelineSetupCommon> getSetups() {
        return setups;
    }

    public void setSetups(List<JenkinsPipelineSetupCommon> setups) {
        this.setups = setups;
    }

    public long getExecuteMaxWaitTime() {
        return executeMaxWaitTime;
    }

    public void setExecuteMaxWaitTime(long executeMaxWaitTime) {
        this.executeMaxWaitTime = executeMaxWaitTime;
    }
}
