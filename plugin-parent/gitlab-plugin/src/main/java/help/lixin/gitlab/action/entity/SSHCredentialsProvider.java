package help.lixin.gitlab.action.entity;

/**
 * ssh
 */
public class SSHCredentialsProvider implements CredentialsProvider {
    private String type = "ssh";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
