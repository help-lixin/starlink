package help.lixin.core.credentials.impl;

import help.lixin.core.credentials.AbstractCredentials;

public class TokenCredentials extends AbstractCredentials {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
