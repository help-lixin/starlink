package help.lixin.starlink.plugin.ansible.request;

import help.lixin.starlink.request.PageRequest;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/29 上午11:35
 * @Description
 */
public class LabelPageListVO extends PageRequest {

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
