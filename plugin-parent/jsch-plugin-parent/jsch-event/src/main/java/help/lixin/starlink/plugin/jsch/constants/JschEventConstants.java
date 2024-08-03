package help.lixin.starlink.plugin.jsch.constants;

import help.lixin.starlink.plugin.jsch.domain.AnsibleHostsUpdateEvent;
import help.lixin.starlink.plugin.jsch.domain.AnsibleLabelDeleteEvent;
import help.lixin.starlink.plugin.jsch.domain.AnsibleLabelUpdateEvent;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/4 下午4:30
 * @Description
 */
public class JschEventConstants {

    public static final String UPDATE_HOSTS_EVENT_TOPIC =
        AnsibleLabelUpdateEvent.class.getName().replaceAll("\\.", "_");
    public static final String DOWNLOAD_KNOWN_HOSTS_EVENT_TOPIC =
        AnsibleHostsUpdateEvent.class.getName().replaceAll("\\.", "_");
    public static final String DELETE_FILE_EVENT_TOPIC = AnsibleLabelDeleteEvent.class.getName().replaceAll("\\.", "_");
}
