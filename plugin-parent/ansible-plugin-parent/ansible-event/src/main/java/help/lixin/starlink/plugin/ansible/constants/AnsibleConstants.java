package help.lixin.starlink.plugin.ansible.constants;

import help.lixin.starlink.plugin.ansible.domain.AnsibleKnownHostEvent;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/4 下午4:37
 * @Description
 */
public class AnsibleConstants {

    public static final String ANSIBLE_KNOWN_HOST_EVENT_TOPIC = AnsibleKnownHostEvent.class.getName().replaceAll("\\.","_");
}
