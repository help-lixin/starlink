package help.lixin.starlink.plugin.jenkins.dto.credential;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/27 7:03 下午
 * @Description
 */
public class TokenDTO {

    private String scope = "GLOBAL";
    private String apiToken;

    private String id;

    private String description;

    @JsonProperty("stapler-class")
    private String staplerClass = "com.dabsquared.gitlabjenkins.connection.GitLabApiTokenImpl";

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStaplerClass() {
        return staplerClass;
    }

    public void setStaplerClass(String staplerClass) {
        this.staplerClass = staplerClass;
    }
}
