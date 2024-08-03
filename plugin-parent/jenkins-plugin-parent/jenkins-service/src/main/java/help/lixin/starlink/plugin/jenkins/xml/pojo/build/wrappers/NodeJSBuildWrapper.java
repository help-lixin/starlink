package help.lixin.starlink.plugin.jenkins.xml.pojo.build.wrappers;

public class NodeJSBuildWrapper extends AbstractBuildWrapper {
    private String nodeJSInstallationName;
    private String cacheLocationStrategy;

    public String getNodeJSInstallationName() {
        return nodeJSInstallationName;
    }

    public void setNodeJSInstallationName(String nodeJSInstallationName) {
        this.nodeJSInstallationName = nodeJSInstallationName;
    }

    public String getCacheLocationStrategy() {
        return cacheLocationStrategy;
    }

    public void setCacheLocationStrategy(String cacheLocationStrategy) {
        this.cacheLocationStrategy = cacheLocationStrategy;
    }

    @Override
    public String toString() {
        // <jenkins.plugins.nodejs.NodeJSBuildWrapper plugin="nodejs@1.6.1">
        //      <nodeJSInstallationName>node-v16</nodeJSInstallationName>
        //      <cacheLocationStrategy class="jenkins.plugins.nodejs.cache.DefaultCacheLocationLocator"/>
        //    </jenkins.plugins.nodejs.NodeJSBuildWrapper>

        StringBuilder builder = new StringBuilder();
        builder.append("<jenkins.plugins.nodejs.NodeJSBuildWrapper").append(" plugin=\"").append(plugin).append("\">").append("\n");
        builder.append("<nodeJSInstallationName>").append(nodeJSInstallationName).append("</nodeJSInstallationName>").append("\n");
        builder.append("<cacheLocationStrategy class=\"jenkins.plugins.nodejs.cache.DefaultCacheLocationLocator\"/>").append("\n");
        builder.append("</jenkins.plugins.nodejs.NodeJSBuildWrapper>");
        return builder.toString();
    }
}
