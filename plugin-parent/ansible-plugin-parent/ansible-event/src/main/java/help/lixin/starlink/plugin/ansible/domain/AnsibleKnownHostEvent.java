package help.lixin.starlink.plugin.ansible.domain;

import help.lixin.starlink.plugin.ansible.event.AnsibleKnownHostsEvent;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/2 下午4:31
 * @Description
 */
public class AnsibleKnownHostEvent implements AnsibleKnownHostsEvent {

    private String knownHostText;

    private String instanceCode;

    public AnsibleKnownHostEvent(String knownHostText, String instanceCode) {
        this.knownHostText = knownHostText;
        this.instanceCode = instanceCode;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }

    public AnsibleKnownHostEvent() {
    }

    public String getKnownHostText() {
        return knownHostText;
    }

    public void setKnownHostText(String knownHostText) {
        this.knownHostText = knownHostText;
    }
}
