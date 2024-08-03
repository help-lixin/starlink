package help.lixin.starlink.core.dto;

public class PropertySource {
    private String pluginCode;
    private String instanceCode;
    // json体
    private String content;

    private String propertiesHash;

    public String getPluginCode() {
        return pluginCode;
    }

    public void setPluginCode(String pluginCode) {
        this.pluginCode = pluginCode;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPropertiesHash() {
        return propertiesHash;
    }

    public void setPropertiesHash(String propertiesHash) {
        this.propertiesHash = propertiesHash;
    }

    @Override
    public String toString() {
        return "PropertySource{" +
                "pluginCode='" + pluginCode + '\'' +
                ", instanceCode='" + instanceCode + '\'' +
                ", content='" + content + '\'' +
                ", propertiesHash='" + propertiesHash + '\'' +
                '}';
    }
}
