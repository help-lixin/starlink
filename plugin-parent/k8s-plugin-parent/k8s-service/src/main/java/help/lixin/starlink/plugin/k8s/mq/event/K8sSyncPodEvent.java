package help.lixin.starlink.plugin.k8s.mq.event;

import io.eventuate.tram.events.common.DomainEvent;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/25 下午2:03
 * @Description
 */
public class K8sSyncPodEvent implements DomainEvent {

    private Long id;
    private String nameSpace;
    private String userName;
    private String name;
    private String instanceCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstanceCode() {
        return instanceCode;
    }

    public void setInstanceCode(String instanceCode) {
        this.instanceCode = instanceCode;
    }
}
