package help.lixin.starlink.plugin.jenkins.xml.pojo.setup;

public class AntSetup extends AbstractSetup {


    private String plugin;
    private String targets;
    private String antName;

    public String getPlugin() {
        return plugin;
    }

    public void setPlugin(String plugin) {
        this.plugin = plugin;
    }

    public String getTargets() {
        return targets;
    }

    public void setTargets(String targets) {
        this.targets = targets;
    }

    public String getAntName() {
        return antName;
    }

    public void setAntName(String antName) {
        this.antName = antName;
    }

    @Override
    public String toString() {
//    <hudson.tasks.Ant plugin="ant@497.v94e7d9fffa_b_9">
//      <targets>jar</targets>
//      <antName>ant-1.9.16</antName>
//    </hudson.tasks.Ant>

        StringBuilder builder = new StringBuilder();
        builder.append("<hudson.tasks.Ant ").append("plugin=\"").append(plugin).append("\"").append(">");
        builder.append("<targets>").append(targets).append("</targets>");
        builder.append("<antName>").append(antName).append("</antName>");
        builder.append("</hudson.tasks.Ant>");
        return builder.toString();
    }
}
