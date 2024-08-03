package help.lixin.starlink.plugin.jenkins.dto.setup;

import help.lixin.starlink.plugin.jenkins.domain.ToolsType;

import java.io.Serializable;

/**
 * Table: jenkins_pipeline_setup_gradle
 */
public class JenkinsPipelineSetupGradleDTO extends JenkinsPipelineSetupCommonDTO implements Serializable {

    private Long gradleId;

    /**
     * 任务命令
     *
     * Column:    task
     * Nullable:  true
     */
    private String task;

    /**
     * 是否直接调用gradle
     *
     * Column:    invoke_gradle
     * Nullable:  true
     */
    private Integer invokeGradle;

    /**
     * 如果你的gradlew脚本没有执行权限，你应该启用这个选项。这将使Jenkins尝试修改gradlew脚本的权限，使其可以执行
     *
     * Column:    make_gradlew_executable
     * Nullable:  true
     */
    private Integer makeGradlewExecutable;

    /**
     * 额外命令
     *
     * Column:    switches
     * Nullable:  true
     */
    private String switches;

    /**
     * 指定工作执行路径
     *
     * Column:    wrapper_location
     * Nullable:  true
     */
    private String wrapperLocation;

    /**
     * 传递给Gradle构建的系统属性
     *
     * Column:    system_properties
     * Nullable:  true
     */
    private String systemProperties;

    /**
     * 传递给Gradle构建的项目属性
     *
     * Column:    project_properties
     * Nullable:  true
     */
    private String projectProperties;

    public JenkinsPipelineSetupGradleDTO() {
        setSetupType(ToolsType.GRADLE);
    }


    public Long getGradleId() {
        return gradleId;
    }

    public void setGradleId(Long gradleId) {
        this.gradleId = gradleId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Integer getInvokeGradle() {
        return invokeGradle;
    }

    public void setInvokeGradle(Integer invokeGradle) {
        this.invokeGradle = invokeGradle;
    }

    public Integer getMakeGradlewExecutable() {
        return makeGradlewExecutable;
    }

    public void setMakeGradlewExecutable(Integer makeGradlewExecutable) {
        this.makeGradlewExecutable = makeGradlewExecutable;
    }

    public String getSwitches() {
        return switches;
    }

    public void setSwitches(String switches) {
        this.switches = switches;
    }

    public String getWrapperLocation() {
        return wrapperLocation;
    }

    public void setWrapperLocation(String wrapperLocation) {
        this.wrapperLocation = wrapperLocation;
    }

    public String getSystemProperties() {
        return systemProperties;
    }

    public void setSystemProperties(String systemProperties) {
        this.systemProperties = systemProperties;
    }

    public String getProjectProperties() {
        return projectProperties;
    }

    public void setProjectProperties(String projectProperties) {
        this.projectProperties = projectProperties;
    }
}