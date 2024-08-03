package help.lixin.starlink.plugin.jenkins.xml.pojo.setup;

public class MavenSetup extends AbstractSetup {
    private String targets;
    private String mavenName;
    private Boolean usePrivateRepository = Boolean.FALSE;

    private Boolean injectBuildVariables = Boolean.FALSE;

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    public String getMavenName() {
        return mavenName;
    }

    public void setMavenName(String mavenName) {
        this.mavenName = mavenName;
    }

    public Boolean getUsePrivateRepository() {
        return usePrivateRepository;
    }

    public void setUsePrivateRepository(Boolean usePrivateRepository) {
        this.usePrivateRepository = usePrivateRepository;
    }

    @Override
    public String toString() {
        // <hudson.tasks.Maven>
        //      <targets>clean install -DskipTests -X</targets>
        //      <mavenName>maven-3.6.3</mavenName>
        //      <usePrivateRepository>false</usePrivateRepository>
        //      <settings class="jenkins.mvn.DefaultSettingsProvider"/>
        //      <globalSettings class="jenkins.mvn.DefaultGlobalSettingsProvider"/>
        //      <injectBuildVariables>false</injectBuildVariables>
        //    </hudson.tasks.Maven>

        StringBuilder builder = new StringBuilder();
        builder.append("<hudson.tasks.Maven>").append("\n");
        builder.append("<targets>").append(targets).append("</targets>").append("\n");
        builder.append("<mavenName>").append(mavenName).append("</mavenName>").append("\n");
        builder.append("<usePrivateRepository>").append(usePrivateRepository).append("</usePrivateRepository>").append("\n");

        builder.append("<settings class=\"jenkins.mvn.DefaultSettingsProvider\"/>").append("\n");
        builder.append("<globalSettings class=\"jenkins.mvn.DefaultGlobalSettingsProvider\"/>").append("\n");

        builder.append("<injectBuildVariables>").append(injectBuildVariables).append("</injectBuildVariables>").append("\n");
        builder.append("</hudson.tasks.Maven>").append("\n");
        return builder.toString();
    }
}
