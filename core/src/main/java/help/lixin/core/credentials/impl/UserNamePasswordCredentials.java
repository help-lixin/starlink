package help.lixin.core.credentials.impl;

import help.lixin.core.credentials.AbstractCredentials;

public class UserNamePasswordCredentials extends AbstractCredentials {
    private String userName;
    private String password;

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

    @Override
    public void setUrl(String url) {

    }

    @Override
    public String getUrl() {
        return null;
    }
}
