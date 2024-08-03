package help.lixin.starlink.plugin.gitlab.service;

import java.util.List;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.gitlab.domain.GitlabUser;
import help.lixin.starlink.plugin.gitlab.dto.base.ChangeStatusDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserCreateDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserPageListDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserSelectOptionDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserUpdateDTO;

public interface IUserService {
    PageResponse<GitlabUser> selectUserList(UserPageListDTO userPageListDTO);

    GitlabUser queryUserByUserId(Long userId);

    GitlabUser queryUserBySysUserId(Long sysUserId);

    int createUser(UserCreateDTO userCreateDTO);

    int deleteUser(Long userId, String instanceCode);

    int updateUser(UserUpdateDTO userUpdateDTO);

    int changeStatus(ChangeStatusDTO changeStatusDTO);

    List<UserSelectOptionDTO> querySelectOption(String instanceCode);
}
