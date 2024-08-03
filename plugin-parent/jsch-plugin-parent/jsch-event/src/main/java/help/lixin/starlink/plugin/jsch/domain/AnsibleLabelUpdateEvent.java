package help.lixin.starlink.plugin.jsch.domain;

import java.util.List;
import java.util.Map;

import help.lixin.starlink.plugin.jsch.event.label.IAnsibleLabelUpdateEvent;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午3:47
 * @Description
 */
public class AnsibleLabelUpdateEvent implements IAnsibleLabelUpdateEvent {
    // 主控机器，目录
    private Map<String, String> masterHostsAndDir;

    private String labelKey;

    // 被控机器
    private List<String> slaveHostsList;

    public AnsibleLabelUpdateEvent() {}

    public AnsibleLabelUpdateEvent(String labelKey, //
        Map<String, String> masterHostsAndDir, //
        List<String> slaveHostsList) {
        this.labelKey = labelKey;
        this.masterHostsAndDir = masterHostsAndDir;
        this.slaveHostsList = slaveHostsList;
    }

    public Map<String, String> getMasterHostsAndDir() {
        return masterHostsAndDir;
    }

    public void setMasterHostsAndDir(Map<String, String> masterHostsAndDir) {
        this.masterHostsAndDir = masterHostsAndDir;
    }

    public String getLabelKey() {
        return labelKey;
    }

    public void setLabelKey(String labelKey) {
        this.labelKey = labelKey;
    }

    public List<String> getSlaveHostsList() {
        return slaveHostsList;
    }

    public void setSlaveHostsList(List<String> slaveHostsList) {
        this.slaveHostsList = slaveHostsList;
    }
}
