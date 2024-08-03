package help.lixin.starlink.plugin.jenkins.xml.pojo.trigger;

public class ReverseBuildTrigger extends AbstractTrigger {

    private String upstreamProjects;

    public String getUpstreamProjects() {
        return upstreamProjects;
    }

    public void setUpstreamProjects(String upstreamProjects) {
        this.upstreamProjects = upstreamProjects;
    }

    @Override
    public String toString() {
        // <jenkins.triggers.ReverseBuildTrigger>
        //      <spec></spec>
        //      <upstreamProjects>go-web-demo, </upstreamProjects>
        //      <threshold>
        //        <name>SUCCESS</name>
        //        <ordinal>0</ordinal>
        //        <color>BLUE</color>
        //        <completeBuild>true</completeBuild>
        //      </threshold>
        //    </jenkins.triggers.ReverseBuildTrigger>

        StringBuilder builder = new StringBuilder();
        builder.append("<jenkins.triggers.ReverseBuildTrigger>").append("\n");
        builder.append("<spec></spec>").append("\n");

        builder.append("<upstreamProjects>").append(upstreamProjects).append("</upstreamProjects>").append("\n");
        builder.append("<threshold>").append("\n");
        builder.append("<name>SUCCESS</name>").append("\n");
        builder.append("<ordinal>0</ordinal>").append("\n");
        builder.append("<color>BLUE</color>").append("\n");
        builder.append("<completeBuild>true</completeBuild>").append("\n");
        builder.append("</threshold>").append("\n");

        builder.append("</jenkins.triggers.ReverseBuildTrigger>");
        return builder.toString();
    }
}
