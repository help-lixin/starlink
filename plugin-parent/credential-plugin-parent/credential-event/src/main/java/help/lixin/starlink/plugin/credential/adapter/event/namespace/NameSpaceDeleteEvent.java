package help.lixin.starlink.plugin.credential.adapter.event.namespace;

import help.lixin.starlink.plugin.credential.domain.namespace.NameSpaceDelete;
import org.springframework.context.ApplicationEvent;

public class NameSpaceDeleteEvent extends ApplicationEvent {

    private NameSpaceDelete nameSpaceDelete;

    public NameSpaceDeleteEvent(NameSpaceDelete nameSpaceDelete) {
        super(nameSpaceDelete);
        this.nameSpaceDelete = nameSpaceDelete;
    }

    public NameSpaceDelete getNameSpaceDelete() {
        return nameSpaceDelete;
    }

}
