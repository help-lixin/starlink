package help.lixin.starlink.plugin.km.api.model;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/15 10:16 上午
 * @Description
 */
public class JmxConfig {

    // jmx端口
    private Integer jmxPort;

    // 最大连接
    private Integer maxConn;

    // 是否开启SSL，如果开始则username 与 token 必须非空
    private Boolean openSSL;

    // SSL情况下的username
    private String username;

    // SSL情况下的token
    private String token;

    public Integer getJmxPort() {
        return jmxPort;
    }

    public void setJmxPort(Integer jmxPort) {
        this.jmxPort = jmxPort;
    }

    public Integer getMaxConn() {
        return maxConn;
    }

    public void setMaxConn(Integer maxConn) {
        this.maxConn = maxConn;
    }

    public Boolean getOpenSSL() {
        return openSSL;
    }

    public void setOpenSSL(Boolean openSSL) {
        this.openSSL = openSSL;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
