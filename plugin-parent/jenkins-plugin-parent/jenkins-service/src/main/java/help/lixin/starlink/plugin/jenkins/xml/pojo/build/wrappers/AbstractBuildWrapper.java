package help.lixin.starlink.plugin.jenkins.xml.pojo.build.wrappers;

import java.io.Serializable;

public abstract class AbstractBuildWrapper implements Serializable {
    protected String plugin;

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public abstract String toString();
}
