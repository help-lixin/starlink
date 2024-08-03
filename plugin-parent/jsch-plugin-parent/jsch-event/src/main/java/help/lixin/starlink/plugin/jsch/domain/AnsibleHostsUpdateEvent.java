package help.lixin.starlink.plugin.jsch.domain;

import java.util.List;

import help.lixin.starlink.plugin.jsch.event.hosts.IAnsibleHostsUpdateEvent;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/22 上午11:06
 * @Description
 */
public class AnsibleHostsUpdateEvent implements IAnsibleHostsUpdateEvent {

    private List<String> instanceCodeList;

    public AnsibleHostsUpdateEvent() {}

    public AnsibleHostsUpdateEvent(List<String> instanceCodeList) {
        this.instanceCodeList = instanceCodeList;
    }

    public List<String> getInstanceCodeList() {
        return instanceCodeList;
    }

    public void setInstanceCodeList(List<String> instanceCodeList) {
        this.instanceCodeList = instanceCodeList;
    }
}
