package help.lixin.starlink.plugin.jsch.domain;

import java.util.Map;

import help.lixin.starlink.plugin.jsch.event.label.IAnsibleLabelDeleteEvent;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/24 下午3:47
 * @Description
 */
public class AnsibleLabelDeleteEvent implements IAnsibleLabelDeleteEvent {
    // 主控机器，目录
    private Map<String, String> masterHostsAndDir;

    private String labelKey;

    public AnsibleLabelDeleteEvent() {}

    public AnsibleLabelDeleteEvent(String labelKey, //
        Map<String, String> masterHostsAndDir) {
        this.labelKey = labelKey;
        this.masterHostsAndDir = masterHostsAndDir;
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
}
