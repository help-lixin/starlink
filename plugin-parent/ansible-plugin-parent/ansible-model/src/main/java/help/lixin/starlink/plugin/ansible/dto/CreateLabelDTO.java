package help.lixin.starlink.plugin.ansible.dto;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/26 下午12:08
 * @Description
 */
public class CreateLabelDTO {
    private String labelKey;

    private String labelName;

    private List<String> inventorys;

    private String createBy;

    public List<String> getInventorys() {
        return inventorys;
    }

    public void setInventorys(List<String> inventorys) {
        this.inventorys = inventorys;
    }

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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
