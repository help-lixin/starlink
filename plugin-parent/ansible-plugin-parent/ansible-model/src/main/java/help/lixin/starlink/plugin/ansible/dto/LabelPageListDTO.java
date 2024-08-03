package help.lixin.starlink.plugin.ansible.dto;

import help.lixin.starlink.core.dto.PageDTO;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/29 上午11:03
 * @Description
 */
public class LabelPageListDTO extends PageDTO {

    private String labelKey;

    private String labelName;

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }
}
