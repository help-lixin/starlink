package help.lixin.starlink.plugin.jenkins.dto.systool;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GlobalMavenConfigDTO {
    @JsonAnySetter
    private Map<String, Object> properties = new HashMap<>();

    @JsonProperty("settingsProvider")
    private MavenSettingsProviderDTO settingsProvider;

    @JsonProperty("globalSettingsProvider")
    private MavenGlobalSettingsProviderDTO globalSettingsProvider;


    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public MavenSettingsProviderDTO getSettingsProvider() {
        return settingsProvider;
    }

    public void setSettingsProvider(MavenSettingsProviderDTO settingsProvider) {
        this.settingsProvider = settingsProvider;
    }

    public MavenGlobalSettingsProviderDTO getGlobalSettingsProvider() {
        return globalSettingsProvider;
    }

    public void setGlobalSettingsProvider(MavenGlobalSettingsProviderDTO globalSettingsProvider) {
        this.globalSettingsProvider = globalSettingsProvider;
    }
}