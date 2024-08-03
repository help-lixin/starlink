package help.lixin.starlink.plugin.ansible.request;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/26 下午12:08
 * @Description
 */
public class CreateLabelVO {
    @NotBlank(message = "标签key不能为空")
    private String labelKey;

    @NotBlank(message = "标签名不能为空")
    private String labelName;

    private List<String> inventorys;


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

}
