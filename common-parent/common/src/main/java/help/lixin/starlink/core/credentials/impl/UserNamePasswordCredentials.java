package help.lixin.starlink.core.credentials.impl;

import help.lixin.starlink.core.credentials.AbstractCredentials;

public class UserNamePasswordCredentials extends AbstractCredentials {
    protected String userName;
    protected String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
