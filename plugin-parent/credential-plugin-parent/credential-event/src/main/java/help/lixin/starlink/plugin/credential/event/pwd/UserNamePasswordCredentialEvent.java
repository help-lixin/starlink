package help.lixin.starlink.plugin.credential.event.pwd;

import help.lixin.starlink.plugin.credential.event.AbstractEvent;

public class UserNamePasswordCredentialEvent extends AbstractEvent {
    private String username;
    private String password;

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
        return "UserNamePasswordCredentialEvent{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", pluginCode='" + pluginCode + '\'' +
                ", instanceCode='" + instanceCode + '\'' +
                ", credentialName='" + credentialName + '\'' +
                ", credentialKey='" + credentialKey + '\'' +
                ", desc='" + remark + '\'' +
                '}';
    }
}
