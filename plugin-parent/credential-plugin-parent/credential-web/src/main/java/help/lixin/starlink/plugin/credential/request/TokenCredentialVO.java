package help.lixin.starlink.plugin.credential.request;

import help.lixin.starlink.plugin.credential.enums.CredentialEnum;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/27 7:09 下午
 * @Description
 */
public class TokenCredentialVO extends CredentialVO{

    private String userName;

    private String token;

    public TokenCredentialVO() {
        setCredentialType(CredentialEnum.TOKEN);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
