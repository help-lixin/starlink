package help.lixin.starlink.plugin.jenkins.api.model;

public enum JenkinsManageToolsModule {
    JDK("hudson.model.JDK"), //
    GIT("hudson.plugins.git.GitTool"), //
    MAVEN("hudson.tasks.Maven$MavenInstallation"), //
    GO("org.jenkinsci.plugins.golang.GolangInstallation"), //
    ANT("hudson.tasks.Ant$AntInstallation"), //
    GRADLE("hudson.plugins.gradle.GradleInstallation"), //
    NODE_JS("jenkins.plugins.nodejs.tools.NodeJSInstallation");

    private String description;

    private JenkinsManageToolsModule(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
