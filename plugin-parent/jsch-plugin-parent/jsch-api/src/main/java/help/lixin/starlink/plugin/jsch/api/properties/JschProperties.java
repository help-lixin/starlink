package help.lixin.starlink.plugin.jsch.api.properties;

public class JschProperties {
    private static final int DEFAULT_PORT = 22;
    // 主机
    private String host;
    // 用户名
    private String username;
    // 密码
    private String password;
    private int port = DEFAULT_PORT;
    // 公钥
    private String publicKey;
    // 私钥
    private String privateKey;
    // 私钥的密钥
    private String privateKeyPassphrase;
    // 连接超时配置
    private int timeout = 60 * 60 * 1000;
    // 连接模式(密钥/密码)
    private Mode mode = Mode.SECRET_KEY;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPrivateKeyPassphrase() {
        return privateKeyPassphrase;
    }

    public void setPrivateKeyPassphrase(String privateKeyPassphrase) {
        this.privateKeyPassphrase = privateKeyPassphrase;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    @Override
    public String toString() {
        return "JschProperties{" + "host='" + host + '\'' + ", username='" + username + '\'' + ", password='" + password
            + '\'' + ", port=" + port + ", publicKey='" + publicKey + '\'' + ", privateKey='" + privateKey + '\''
            + ", privateKeyPassphrase='" + privateKeyPassphrase + '\'' + ", timeout=" + timeout + '}';
    }
}
