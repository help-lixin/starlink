package help.lixin.starlink.plugin.jenkins.xml.pojo.build.wrappers;

public class GradleBuildWrapper extends AbstractBuildWrapper {

    private String gradleName;
    private String tasks;

    public String getGradleName() {
        return gradleName;
    }

    public void setGradleName(String gradleName) {
        this.gradleName = gradleName;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
//        <hudson.plugins.gradle.Gradle plugin="gradle@2.9">
//      <switches></switches>
//      <tasks>assemble</tasks>
//      <rootBuildScriptDir></rootBuildScriptDir>
//      <buildFile></buildFile>
//      <gradleName>gradle-7.6.3</gradleName>
//      <useWrapper>false</useWrapper>
//      <makeExecutable>false</makeExecutable>
//      <useWorkspaceAsHome>false</useWorkspaceAsHome>
//      <wrapperLocation></wrapperLocation>
//      <passAllAsSystemProperties>false</passAllAsSystemProperties>
//      <projectProperties></projectProperties>
//      <passAllAsProjectProperties>false</passAllAsProjectProperties>
//    </hudson.plugins.gradle.Gradle>

        StringBuilder builder = new StringBuilder();
        builder.append("<hudson.plugins.gradle.Gradle ").append(" plugin=\"").append(plugin).append("\"").append(">");
        builder.append("<switches></switches>");
        builder.append("<tasks>").append(tasks).append("</tasks>");
        builder.append("<rootBuildScriptDir></rootBuildScriptDir>");
        builder.append("<buildFile></buildFile>");
        builder.append("<gradleName>").append(gradleName).append("</gradleName>");
        builder.append("<useWrapper>false</useWrapper>");
        builder.append("<makeExecutable>false</makeExecutable>");
        builder.append("<useWorkspaceAsHome>false</useWorkspaceAsHome>");
        builder.append("<wrapperLocation></wrapperLocation>");
        builder.append("<passAllAsSystemProperties>false</passAllAsSystemProperties>");
        builder.append("<projectProperties></projectProperties>");
        builder.append("<passAllAsProjectProperties>false</passAllAsProjectProperties>");
        builder.append("</hudson.plugins.gradle.Gradle>");
        return builder.toString();
    }
}
