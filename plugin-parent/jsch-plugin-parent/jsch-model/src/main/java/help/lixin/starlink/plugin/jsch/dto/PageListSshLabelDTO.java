package help.lixin.starlink.plugin.jsch.dto;

import help.lixin.starlink.core.dto.PageDTO;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/23 上午11:27
 * @Description
 */
public class PageListSshLabelDTO extends PageDTO {

    /**
     * 标签key
     */
    private String labelKey;

    /**
     * 标签名
     */
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
