package help.lixin.starlink.plugin.credential.adapter.event.secret;

import help.lixin.starlink.plugin.credential.event.secret.SecretCredentialEvent;
import org.springframework.context.ApplicationEvent;

public class SecretCredentialAdapterEvent extends ApplicationEvent {

    private SecretCredentialEvent secretCredentialEvent;

    public SecretCredentialAdapterEvent(SecretCredentialEvent secretCredentialEvent) {
        super(secretCredentialEvent);
        this.secretCredentialEvent = secretCredentialEvent;
    }

    public SecretCredentialEvent getSecretCredentialEvent() {
        return secretCredentialEvent;
    }
}
