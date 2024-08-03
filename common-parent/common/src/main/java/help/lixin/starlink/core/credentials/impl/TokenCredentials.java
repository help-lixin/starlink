package help.lixin.starlink.core.credentials.impl;

import help.lixin.starlink.core.credentials.AbstractCredentials;

public class TokenCredentials extends AbstractCredentials {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
