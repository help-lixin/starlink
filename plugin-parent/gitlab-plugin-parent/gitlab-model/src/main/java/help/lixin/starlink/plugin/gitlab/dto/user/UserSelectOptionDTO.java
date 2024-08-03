package help.lixin.starlink.plugin.gitlab.dto.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @Author: 伍岳林
 * @Date: 2023/11/30 4:46 下午
 * @Description
 */
public class UserSelectOptionDTO {

    private String label;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long value;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }
}
