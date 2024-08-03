package help.lixin.starlink.plugin.rocketmq.api.dto.consumer;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 2:52 下午
 * @Description
 */
public class ConsumerClientInfoDTO {

    private String clientId;
    private String clientAddr;
    private String language;
    private Integer version;
    private String versionDesc;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientAddr() {
        return clientAddr;
    }

    public void setClientAddr(String clientAddr) {
        this.clientAddr = clientAddr;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }
}
