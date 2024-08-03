package help.lixin.starlink.plugin.nacos.request.servicemanage;

import help.lixin.starlink.request.PageRequest;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/19 12:22 下午
 * @Description
 */
public class NacosServicePageListRequest extends PageRequest {

    private String nameSpaceId;

    private String groupName;

    private String selector;

    public String getNameSpaceId() {
        return nameSpaceId;
    }

    public void setNameSpaceId(String nameSpaceId) {
        this.nameSpaceId = nameSpaceId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }
}
