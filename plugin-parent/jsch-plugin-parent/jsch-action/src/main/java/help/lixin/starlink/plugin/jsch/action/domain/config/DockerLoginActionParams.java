package help.lixin.starlink.plugin.jsch.action.domain.config;

public class DockerLoginActionParams {
    // 命名空间
    private String namespace;
    // 镜像的域名
    private String imageRepository;
    // 用户名
    private String userName;
    // 密码
    private String password;

    // 是否加密
    private Boolean isEncryption = Boolean.TRUE;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getImageRepository() {
        return imageRepository;
    }

    public void setImageRepository(String imageRepository) {
        this.imageRepository = imageRepository;
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

    public Boolean getEncryption() {
        return isEncryption;
    }

    public void setEncryption(Boolean encryption) {
        isEncryption = encryption;
    }
}
