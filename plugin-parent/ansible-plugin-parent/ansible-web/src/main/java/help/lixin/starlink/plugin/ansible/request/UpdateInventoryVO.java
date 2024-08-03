package help.lixin.starlink.plugin.ansible.request;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/26 下午12:08
 * @Description
 */
public class UpdateInventoryVO {

    private List<String> inventorys;

    @NotNull(message = "id不能为空")
    private Long id;

    private String labelName;

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getInventorys() {
        return inventorys;
    }

    public void setInventorys(List<String> inventorys) {
        this.inventorys = inventorys;
    }


}
