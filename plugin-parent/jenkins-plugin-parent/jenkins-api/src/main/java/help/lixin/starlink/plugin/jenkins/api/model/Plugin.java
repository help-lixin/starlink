package help.lixin.starlink.plugin.jenkins.api.model;

public class Plugin {
    // true
    private Boolean active;
    // Jenkins Apache HttpComponents Client 4.x API Plugin
    private String longName;
    // apache-httpcomponents-client-4-api
    private String shortName;
    // 4.5.14-208.v438351942757
    private String version;
    // https://plugins.jenkins.io/apache-httpcomponents-client-4-api
    private String url;
    // 对jenkins要求的版本
    private String requiredCoreVersion;
    // 是否可更新
    private Boolean hasUpdate;

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getLongName() {
        return longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequiredCoreVersion() {
        return requiredCoreVersion;
    }

    public void setRequiredCoreVersion(String requiredCoreVersion) {
        this.requiredCoreVersion = requiredCoreVersion;
    }

    public Boolean getHasUpdate() {
        return hasUpdate;
    }

    public void setHasUpdate(Boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }
}
