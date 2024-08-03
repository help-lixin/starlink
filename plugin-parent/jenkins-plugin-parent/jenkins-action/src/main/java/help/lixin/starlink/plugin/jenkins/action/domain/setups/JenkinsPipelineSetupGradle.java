package help.lixin.starlink.plugin.jenkins.action.domain.setups;

import java.io.Serializable;

public class JenkinsPipelineSetupGradle extends JenkinsPipelineSetupCommon implements Serializable {

    private Long gradleId;

    private String task;

    private Integer invokeGradle;

    private Integer makeGradlewExecutable;

    private String switches;

    private String wrapperLocation;

    private String systemProperties;

    private String projectProperties;

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