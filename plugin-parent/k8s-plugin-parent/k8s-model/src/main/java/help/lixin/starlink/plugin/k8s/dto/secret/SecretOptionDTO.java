package help.lixin.starlink.plugin.k8s.dto.secret;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/14 下午6:52
 * @Description
 */
public class SecretOptionDTO {

    private String label;
    private String value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
