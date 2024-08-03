package help.lixin.starlink.plugin.gitlab.mq.convert;

import help.lixin.event.user.SystemUserCreateEvent;
import help.lixin.event.user.SystemUserUpdateEvent;
import help.lixin.starlink.plugin.gitlab.dto.user.UserCreateDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserUpdateDTO;
import org.mapstruct.Mapper;

@Mapper
public interface GitlabUserMQConvert {

    UserCreateDTO convert(SystemUserCreateEvent systemUserCreateEvent);
    UserUpdateDTO convert(SystemUserUpdateEvent systemUserUpdateEvent);
}
