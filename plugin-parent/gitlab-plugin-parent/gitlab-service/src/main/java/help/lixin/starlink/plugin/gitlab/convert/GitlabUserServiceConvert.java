package help.lixin.starlink.plugin.gitlab.convert;

import help.lixin.starlink.plugin.gitlab.domain.GitlabUser;
import help.lixin.starlink.plugin.gitlab.dto.user.UserCreateDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserUpdateDTO;
import org.mapstruct.Mapper;

@Mapper
public interface GitlabUserServiceConvert {

    GitlabUser convert(UserUpdateDTO userUpdateDTO);
    GitlabUser convert(UserCreateDTO userUpdateDTO);
}
