package help.lixin.starlink.plugin.credential.request;

import help.lixin.starlink.plugin.credential.enums.CredentialEnum;

public class UserNamePasswordCredentialVO extends CredentialVO {
    private String userName;
    private String password;
    private String imgDomain;

    public UserNamePasswordCredentialVO() {
        setCredentialType(CredentialEnum.USERNAME_PASSWORD);
    }

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

    public String getImgDomain() {
        return imgDomain;
    }

    public void setImgDomain(String imgDomain) {
        this.imgDomain = imgDomain;
    }

    @Override
    public String toString() {
        return "UserNamePasswordCredentialEvent{" +
                "username='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", pluginCode='" + pluginCode + '\'' +
                ", instanceCode='" + instanceCode + '\'' +
                ", credentialName='" + credentialName + '\'' +
                ", credentialKey='" + credentialKey + '\'' +
                ", desc='" + remark + '\'' +
                '}';
    }
}
