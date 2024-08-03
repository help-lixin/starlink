package help.lixin.starlink.plugin.github.action.entity;

/**
 * http/http : token
 */
public class TokenCredentialsProvider implements CredentialsProvider {

    private String type = "token";
    private String token;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
