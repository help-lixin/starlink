package help.lixin.starlink.plugin.ansible.dto;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/26 下午12:08
 * @Description
 */
public class UpdateInventoryDTO {

    private List<String> inventorys;

    private Long id;

    private String labelName;

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public List<String> getInventorys() {
        return inventorys;
    }

    public void setInventorys(List<String> inventorys) {
        this.inventorys = inventorys;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
