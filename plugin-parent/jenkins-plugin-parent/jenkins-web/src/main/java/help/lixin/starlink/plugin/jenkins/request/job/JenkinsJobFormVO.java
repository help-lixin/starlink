package help.lixin.starlink.plugin.jenkins.request.job;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import help.lixin.starlink.plugin.jenkins.domain.ProjectType;
import help.lixin.starlink.plugin.jenkins.domain.ScmType;
import help.lixin.starlink.plugin.jenkins.domain.ToolsType;
import help.lixin.starlink.plugin.jenkins.request.build.JenkinsPipelineScmVO;
import help.lixin.starlink.plugin.jenkins.request.params.JenkinsParamVO;
import help.lixin.starlink.plugin.jenkins.request.setup.JenkinsPipelineSetupCommonVO;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/13 9:48 上午
 * @Description
 */
public class JenkinsJobFormVO {

    // jenkins支持( FreeStyle Project / Maven Project / Pipeline Project )
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
    private JenkinsPipelineScmVO scm;

    private List<JenkinsParamVO> params = new ArrayList<>(0);

    // 对应的是构建依赖表里的id
    private List<String> buildDependencys = new ArrayList<>();

    private List<JenkinsPipelineSetupCommonVO> setups;

    private Long id;

    @NotBlank(message = "实例编码不能为空")
    private String instanceCode;

    /**
     * 任务名
     */
    @NotBlank(message = "任务名称不能为空")
    private String jobName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态值
     */
    private Integer status;

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

    public List<String> getBuildDependencys() {
        return buildDependencys;
    }

    public void setBuildDependencys(List<String> buildDependencys) {
        this.buildDependencys = buildDependencys;
    }

    public JenkinsPipelineScmVO getScm() {
        return scm;
    }

    public void setScm(JenkinsPipelineScmVO scm) {
        this.scm = scm;
    }

    public List<JenkinsParamVO> getParams() {
        return params;
    }

    public void setParams(List<JenkinsParamVO> params) {
        this.params = params;
    }

    public List<JenkinsPipelineSetupCommonVO> getSetups() {
        return setups;
    }

    public void setSetups(List<JenkinsPipelineSetupCommonVO> setups) {
        this.setups = setups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
