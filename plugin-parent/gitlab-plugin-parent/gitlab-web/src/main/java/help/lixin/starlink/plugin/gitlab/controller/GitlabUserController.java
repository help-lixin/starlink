package help.lixin.starlink.plugin.gitlab.controller;

import static help.lixin.response.Response.fail;
import static help.lixin.response.Response.success;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.gitlab.convert.GitlabControllerConvert;
import help.lixin.starlink.plugin.gitlab.convert.GitlabUserControllerConvert;
import help.lixin.starlink.plugin.gitlab.domain.GitlabUser;
import help.lixin.starlink.plugin.gitlab.dto.base.ChangeStatusDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserCreateDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserPageListDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserSelectOptionDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserUpdateDTO;
import help.lixin.starlink.plugin.gitlab.request.base.ChangeStatusVO;
import help.lixin.starlink.plugin.gitlab.request.user.UserDeleteVO;
import help.lixin.starlink.plugin.gitlab.request.user.UserPageListVO;
import help.lixin.starlink.plugin.gitlab.request.user.UserUpdateVO;
import help.lixin.starlink.plugin.gitlab.request.user.UserVO;
import help.lixin.starlink.plugin.gitlab.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户信息相关接口")
@RestController
@RequestMapping("/gitlab/user")
public class GitlabUserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/add")
    @ApiOperation(value = "添加用户信息")
    public Response<Integer> createUser(@Valid @RequestBody UserVO userVO) {
        GitlabUserControllerConvert userConvert = Mappers.getMapper(GitlabUserControllerConvert.class);
        UserCreateDTO userCreateDTO = userConvert.userConvert(userVO);
        userCreateDTO.setInstanceCode(userVO.getInstanceCode());
        userCreateDTO.setCreateBy(UserContext.getUser().getUserName());

        return success(userService.createUser(userCreateDTO));
    }

    @GetMapping("/info/{userId}")
    @ApiOperation(value = "根据id查询信息")
    public Response<GitlabUser> queryByUserId(@PathVariable Long userId) {
        return success(userService.queryUserByUserId(userId));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询分页信息")
    public Response<PageResponse<GitlabUser>> queryUser(UserPageListVO userPageListVO) {
        GitlabUserControllerConvert gitlabUserConvert = Mappers.getMapper(GitlabUserControllerConvert.class);
        UserPageListDTO userPageListDTO = gitlabUserConvert.userConvert(userPageListVO);
        return success(userService.selectUserList(userPageListDTO));
    }

    @DeleteMapping("/del")
    @ApiOperation(value = "删除用户")
    public Response<Integer> removeUser(@Valid @RequestBody UserDeleteVO userDeleteVO) {
        return success(userService.deleteUser(userDeleteVO.getUserId(), userDeleteVO.getInstanceCode()));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "更新用户")
    public Response<Integer> updateUser(@Valid @RequestBody UserUpdateVO userUpdateVO) {
        String pwd = userUpdateVO.getPwd();
        if (StringUtils.isNotBlank(pwd) && pwd.length() < 6) {
            return fail("密码不为空的情况下，长度不能小于6位");
        }
        GitlabUserControllerConvert userConvert = Mappers.getMapper(GitlabUserControllerConvert.class);
        UserUpdateDTO userUpdateDTO = userConvert.userConvert(userUpdateVO);
        userUpdateDTO.setUpdateBy(UserContext.getUser().getUserName());
        return success(userService.updateUser(userUpdateDTO));
    }

    @PutMapping("/changeStatus")
    @ApiOperation(value = "更新用户状态")
    public Response<Integer> changeStatus(@RequestBody ChangeStatusVO changeStatusVO) {
        GitlabControllerConvert gitlabConvert = Mappers.getMapper(GitlabControllerConvert.class);
        ChangeStatusDTO changeStatusDTO = gitlabConvert.convert(changeStatusVO);
        changeStatusDTO.setCreateBy(UserContext.getUser().getUserName());
        return success(userService.changeStatus(changeStatusDTO));
    }

    @ApiOperation(value = "根据instanceCode查询对应用户下拉列表")
    @GetMapping("/selectOption/{instanceCode}")
    public Response<List<UserSelectOptionDTO>> selectOption(@PathVariable String instanceCode) {
        return success(userService.querySelectOption(instanceCode));
    }

}
