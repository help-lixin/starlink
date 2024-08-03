package help.lixin.starlink.plugin.jsch.api.service;

import com.jcraft.jsch.Channel;

public interface ICommandExecuteCallback {
    void connectBeforeCallback(Channel channel);
}
