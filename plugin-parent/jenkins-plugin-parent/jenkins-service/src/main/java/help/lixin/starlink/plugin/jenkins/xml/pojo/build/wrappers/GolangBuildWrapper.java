package help.lixin.starlink.plugin.jenkins.xml.pojo.build.wrappers;

public class GolangBuildWrapper extends AbstractBuildWrapper {
    private String goVersion;

    public String getGoVersion() {
        return goVersion;
    }

    public void setGoVersion(String goVersion) {
        this.goVersion = goVersion;
    }

    @Override
    public String toString() {
        // <org.jenkinsci.plugins.golang.GolangBuildWrapper plugin="golang@1.4">
        //      <goVersion>go1.21.3</goVersion>
        //    </org.jenkinsci.plugins.golang.GolangBuildWrapper>
        //  </buildWrappers>
        StringBuilder builder = new StringBuilder();
        builder.append("<org.jenkinsci.plugins.golang.GolangBuildWrapper").append(" plugin=\"").append(plugin).append("\">").append("\n");
        builder.append("<goVersion>").append(goVersion).append("</goVersion>").append("\n");
        builder.append("</org.jenkinsci.plugins.golang.GolangBuildWrapper>");
        return builder.toString();
    }
}
