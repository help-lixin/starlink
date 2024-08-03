package help.lixin.starlink.plugin.credential.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/25 3:27 下午
 * @Description
 */
public class SysCredentialOptionDTO {

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
