package help.lixin.gitlab.action.entity;

/**
 * http/https : username + password
 */
public class UsernamePasswordCredentialsProvider implements CredentialsProvider {
    private String username;
    private String password;
    private String type = "username-password";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsernamePasswordCredentialsProvider{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
