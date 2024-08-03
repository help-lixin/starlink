package help.lixin.starlink.plugin.jenkins.xml.pojo.setup;

public class ShellSetup extends AbstractSetup {
    private String command;

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        // <hudson.tasks.Shell>
        //      <command>echo $JAVA_HOME</command>
        //      <configuredLocalRules/>
        //    </hudson.tasks.Shell>
        StringBuilder builder = new StringBuilder();
        builder.append("<hudson.tasks.Shell>").append("\n");
        builder.append("<command>").append(command).append("</command>").append("\n");
        builder.append("<configuredLocalRules/>").append("\n");
        builder.append("</hudson.tasks.Shell>").append("\n");
        return builder.toString();
    }
}
