package help.lixin.starlink.plugin.jenkins.xml.pojo.scm;

public class SvnScm extends AbstractScm {
    private String remote;

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        // <scm class="hudson.scm.SubversionSCM" plugin="subversion@2.17.3">
        //    <locations>
        //      <hudson.scm.SubversionSCM_-ModuleLocation>
        //        <remote>svn://192.168.1.26/spring-web-demo</remote>
        //        <credentialsId>svn-pwd</credentialsId>
        //        <local>.</local>
        //        <depthOption>infinity</depthOption>
        //        <ignoreExternalsOption>true</ignoreExternalsOption>
        //        <cancelProcessOnExternalsFail>true</cancelProcessOnExternalsFail>
        //      </hudson.scm.SubversionSCM_-ModuleLocation>
        //    </locations>
        //    <excludedRegions></excludedRegions>
        //    <includedRegions></includedRegions>
        //    <excludedUsers></excludedUsers>
        //    <excludedRevprop></excludedRevprop>
        //    <excludedCommitMessages></excludedCommitMessages>
        //    <workspaceUpdater class="hudson.scm.subversion.UpdateUpdater"/>
        //    <ignoreDirPropChanges>false</ignoreDirPropChanges>
        //    <filterChangelog>false</filterChangelog>
        //    <quietOperation>true</quietOperation>
        //  </scm>

        builder.append("<scm class=\"hudson.scm.SubversionSCM\"").append("  plugin=\"").append(plugin).append("\">").append("\n");
        builder.append("<locations>").append("\n");
        builder.append("<hudson.scm.SubversionSCM_-ModuleLocation>").append("\n");

        builder.append("<remote>").append(remote).append("</remote>").append("\n");
        builder.append("<credentialsId>").append(credentialsId).append("</credentialsId>").append("\n");
        builder.append("<local>.</local>").append("\n");
        builder.append("<depthOption>infinity</depthOption>").append("\n");
        builder.append("<ignoreExternalsOption>true</ignoreExternalsOption>").append("\n");
        builder.append("<cancelProcessOnExternalsFail>true</cancelProcessOnExternalsFail>").append("\n");
        builder.append("</hudson.scm.SubversionSCM_-ModuleLocation>").append("\n");
        builder.append("</locations>").append("\n");

        builder.append("<excludedRegions></excludedRegions>").append("\n");
        builder.append("<includedRegions></includedRegions>").append("\n");
        builder.append("<excludedUsers></excludedUsers>").append("\n");
        builder.append("<excludedRevprop></excludedRevprop>").append("\n");
        builder.append("<excludedCommitMessages></excludedCommitMessages>").append("\n");
        builder.append("<workspaceUpdater class=\"hudson.scm.subversion.UpdateUpdater\"/>").append("\n");
        builder.append("<ignoreDirPropChanges>false</ignoreDirPropChanges>").append("\n");
        builder.append("<filterChangelog>false</filterChangelog>").append("\n");
        builder.append("<quietOperation>true</quietOperation>").append("\n");
        builder.append("</scm>").append("\n");
        return builder.toString();
    }
}
