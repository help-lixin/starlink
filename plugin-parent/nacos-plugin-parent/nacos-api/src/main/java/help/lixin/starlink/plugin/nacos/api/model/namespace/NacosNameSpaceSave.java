package help.lixin.starlink.plugin.nacos.api.model.namespace;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/20 5:41 下午
 * @Description
 */
public class NacosNameSpaceSave {

    private String nameSpaceId;

    private String nameSpaceName;

    private String nameSpaceDesc;

    public String getNameSpaceId() {
        return nameSpaceId;
    }

    public void setNameSpaceId(String nameSpaceId) {
        this.nameSpaceId = nameSpaceId;
    }

    public String getNameSpaceName() {
        return nameSpaceName;
    }

    public void setNameSpaceName(String nameSpaceName) {
        this.nameSpaceName = nameSpaceName;
    }

    public String getNameSpaceDesc() {
        return nameSpaceDesc;
    }

    public void setNameSpaceDesc(String nameSpaceDesc) {
        this.nameSpaceDesc = nameSpaceDesc;
    }
}
