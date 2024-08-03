package help.lixin.starlink.plugin.jenkins.dto.systool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitToolInfoDTO {
    @JsonProperty("stapler-class")
    private String staplerClass = "hudson.plugins.git.GitTool";

    @JsonProperty("$class")
    private String className = "hudson.plugins.git.GitTool";

    @JsonProperty("name")
    private String name;

    @JsonProperty("home")
    private String home;

    @JsonProperty("properties")
    private Map<String, String> properties = new HashMap<>();

    public String getStaplerClass() {
        return staplerClass;
    }

    public void setStaplerClass(String staplerClass) {
        this.staplerClass = staplerClass;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public Map<String, String> getProperties() {
        if (properties.size() == 0){
            properties.put("stapler-class-bag","true");
        }

        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }
}