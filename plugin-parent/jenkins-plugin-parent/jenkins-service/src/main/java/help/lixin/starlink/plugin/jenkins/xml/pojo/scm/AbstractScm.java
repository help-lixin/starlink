package help.lixin.starlink.plugin.jenkins.xml.pojo.scm;

public abstract class AbstractScm {
    protected String plugin;
    protected String credentialsId;

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public String getCredentialsId() {
        return credentialsId;
    }

    public void setCredentialsId(String credentialsId) {
        this.credentialsId = credentialsId;
    }

    public abstract String toString();
}
