package help.lixin.starlink.plugin.gitlab.convert;

import help.lixin.starlink.plugin.gitlab.dto.user.UserCreateDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserPageListDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserUpdateDTO;
import help.lixin.starlink.plugin.gitlab.request.user.UserPageListVO;
import help.lixin.starlink.plugin.gitlab.request.user.UserUpdateVO;
import help.lixin.starlink.plugin.gitlab.request.user.UserVO;
import org.mapstruct.Mapper;

@Mapper
public interface GitlabUserControllerConvert {

    UserCreateDTO userConvert(UserVO userVO);

    UserUpdateDTO userConvert(UserUpdateVO userUpdateVO);

    UserPageListDTO userConvert(UserPageListVO userPageListVO);
}
