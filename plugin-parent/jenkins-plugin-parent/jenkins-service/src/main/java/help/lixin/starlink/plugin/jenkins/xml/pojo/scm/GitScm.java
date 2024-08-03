package help.lixin.starlink.plugin.jenkins.xml.pojo.scm;

public class GitScm extends AbstractScm {
    private String url;
    private String branch;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        // <scm class="hudson.plugins.git.GitSCM" plugin="git@5.2.0">
        //    <configVersion>2</configVersion>
        //    <userRemoteConfigs>
        //      <hudson.plugins.git.UserRemoteConfig>
        //        <url>http://192.168.1.10/root/spring-web-demo.git</url>
        //        <credentialsId>gitlab</credentialsId>
        //      </hudson.plugins.git.UserRemoteConfig>
        //    </userRemoteConfigs>
        //    <branches>
        //      <hudson.plugins.git.BranchSpec>
        //        <name>*/main</name>
        //      </hudson.plugins.git.BranchSpec>
        //    </branches>
        //    <doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>
        //    <submoduleCfg class="empty-list"/>
        //    <extensions/>
        //  </scm>
        StringBuilder builder = new StringBuilder();
        builder.append("<scm class=\"hudson.plugins.git.GitSCM\"").append("  plugin=\"").append(plugin).append("\">").append("\n");
        builder.append("<configVersion>2</configVersion>").append("\n");
        builder.append("<userRemoteConfigs>").append("\n");
        builder.append("<hudson.plugins.git.UserRemoteConfig>").append("\n");
        builder.append("<url>").append(url).append("</url>").append("\n");
        builder.append("<credentialsId>").append(credentialsId).append("</credentialsId>").append("\n");
        builder.append("</hudson.plugins.git.UserRemoteConfig>").append("\n");
        builder.append("</userRemoteConfigs>").append("\n");

        builder.append("<branches>").append("\n");
        builder.append("<hudson.plugins.git.BranchSpec>").append("\n");
        builder.append("<name>").append(branch).append("</name>").append("\n");
        builder.append("</hudson.plugins.git.BranchSpec>").append("\n");
        builder.append("</branches>").append("\n");

        builder.append("<doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>").append("\n");
        builder.append("<submoduleCfg class=\"empty-list\"/>").append("\n");
        builder.append("<extensions/>").append("\n");

        builder.append("</scm>").append("\n");
        return builder.toString();
    }
}
