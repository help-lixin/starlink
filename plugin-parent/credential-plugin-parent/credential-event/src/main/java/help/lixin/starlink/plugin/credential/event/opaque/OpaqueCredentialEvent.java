package help.lixin.starlink.plugin.credential.event.opaque;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/4 上午9:34
 * @Description
 */
public class OpaqueCredentialEvent {

    private String key;

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
