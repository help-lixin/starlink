package help.lixin.starlink.plugin.gitlab.dto;

/**
 * @Author: 伍岳林
 * @Date: 2024/3/24 下午3:38
 * @Description 下拉查询列表
 */
public class OptionListDTO {
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
