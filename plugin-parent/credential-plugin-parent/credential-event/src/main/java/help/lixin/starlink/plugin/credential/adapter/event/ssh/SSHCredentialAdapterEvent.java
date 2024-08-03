package help.lixin.starlink.plugin.credential.adapter.event.ssh;

import help.lixin.starlink.plugin.credential.event.ssh.SSHCredentialEvent;
import org.springframework.context.ApplicationEvent;

public class SSHCredentialAdapterEvent extends ApplicationEvent {

    private SSHCredentialEvent sshCredentialEvent;

    public SSHCredentialAdapterEvent(SSHCredentialEvent sshCredentialEvent) {
        super(sshCredentialEvent);
        this.sshCredentialEvent = sshCredentialEvent;
    }

    public SSHCredentialEvent getSshCredentialEvent() {
        return sshCredentialEvent;
    }
}
