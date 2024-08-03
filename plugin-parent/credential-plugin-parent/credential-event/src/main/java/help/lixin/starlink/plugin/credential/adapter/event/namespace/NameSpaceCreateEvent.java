package help.lixin.starlink.plugin.credential.adapter.event.namespace;

import help.lixin.starlink.plugin.credential.domain.namespace.NameSpaceCreate;
import org.springframework.context.ApplicationEvent;

public class NameSpaceCreateEvent extends ApplicationEvent {

    private NameSpaceCreate nameSpaceCreate;

    public NameSpaceCreateEvent(NameSpaceCreate nameSpaceCreate) {
        super(nameSpaceCreate);
        this.nameSpaceCreate = nameSpaceCreate;
    }

    public NameSpaceCreate getNameSpaceCreateEvent() {
        return nameSpaceCreate;
    }
}
