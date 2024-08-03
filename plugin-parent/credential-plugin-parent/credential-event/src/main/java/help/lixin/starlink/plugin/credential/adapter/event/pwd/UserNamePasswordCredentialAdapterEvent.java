package help.lixin.starlink.plugin.credential.adapter.event.pwd;

import help.lixin.starlink.plugin.credential.event.pwd.UserNamePasswordCredentialEvent;
import org.springframework.context.ApplicationEvent;

public class UserNamePasswordCredentialAdapterEvent extends ApplicationEvent {

    private UserNamePasswordCredentialEvent userNamePasswordCredentialEvent;

    public UserNamePasswordCredentialAdapterEvent(UserNamePasswordCredentialEvent userNamePasswordCredentialEvent) {
        super(userNamePasswordCredentialEvent);
        this.userNamePasswordCredentialEvent = userNamePasswordCredentialEvent;
    }

    public UserNamePasswordCredentialEvent getUserNamePasswordCredentialEvent() {
        return userNamePasswordCredentialEvent;
    }
}
