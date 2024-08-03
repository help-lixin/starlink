package help.lixin.starlink.plugin.gitlab.service.impl;

import java.util.Date;
import java.util.List;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.UserApi;
import org.gitlab4j.api.models.User;
import org.jasypt.encryption.StringEncryptor;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.gitlab.convert.GitlabUserServiceConvert;
import help.lixin.starlink.plugin.gitlab.domain.GitlabUser;
import help.lixin.starlink.plugin.gitlab.dto.base.ChangeStatusDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserCreateDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserPageListDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserSelectOptionDTO;
import help.lixin.starlink.plugin.gitlab.dto.user.UserUpdateDTO;
import help.lixin.starlink.plugin.gitlab.mapper.GitlabUserMapper;
import help.lixin.starlink.plugin.gitlab.service.IUserService;

public class GitlabUserService implements IUserService {

    private GitlabUserMapper userMapper;
    private StringEncryptor stringEncryptor;
    private QueryTemplate queryTemplate;

    private final AbstractServiceFactory gitlabServiceFactory;

    public GitlabUserService(AbstractServiceFactory gitlabServiceFactory, GitlabUserMapper userMapper,
        StringEncryptor stringEncryptor, QueryTemplate queryTemplate) {
        this.gitlabServiceFactory = gitlabServiceFactory;
        this.userMapper = userMapper;
        this.stringEncryptor = stringEncryptor;
        this.queryTemplate = queryTemplate;
    }

    @Override
    public PageResponse<GitlabUser> selectUserList(UserPageListDTO userPageListDTO) {
        return queryTemplate.execute(userPageListDTO, () -> {
            userMapper.selectUserList(userPageListDTO);
        });
    }

    @Override
    public GitlabUser queryUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public GitlabUser queryUserBySysUserId(Long sysUserId) {
        return userMapper.queryUserBySysUserId(sysUserId);
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public int createUser(UserCreateDTO userCreateDTO) {
        String userName = userCreateDTO.getUserName();

        GitlabUser gitlabUserInfo = userMapper.selectUser(userName);
        if (gitlabUserInfo != null && gitlabUserInfo.getStatus() == 1) {
            throw new ServiceException("已存在该用户，不能再次创建");
        }

        UserApi userApi = gitlabServiceFactory.getInstance(userCreateDTO.getInstanceCode(), UserApi.class);

        User user = new User();
        user.setUsername(userName);
        user.setName(userCreateDTO.getNickName());
        user.setEmail(userCreateDTO.getEmail());
        User createGitlabUser = null;
        try {
            createGitlabUser = userApi.createUser(user, userCreateDTO.getPwd(), Boolean.FALSE);
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常：" + e.getMessage());
        }

        GitlabUserServiceConvert mapper = Mappers.getMapper(GitlabUserServiceConvert.class);
        GitlabUser gitlabUser = mapper.convert(userCreateDTO);

        String encPwd = stringEncryptor.encrypt(userCreateDTO.getPwd());
        gitlabUser.setPassword(encPwd);
        gitlabUser.setGitlabUserId(createGitlabUser.getId());

        if (gitlabUserInfo == null) {
            gitlabUser.setUpdateBy(userCreateDTO.getCreateBy());
            return userMapper.insertSelective(gitlabUser);
        } else {
            gitlabUser.setId(gitlabUserInfo.getId());
            return userMapper.updateByPrimaryKey(gitlabUser);
        }

    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public int deleteUser(Long userId, String instanceCode) {

        GitlabUser gitlabUser = userMapper.selectByPrimaryKey(userId);
        if (gitlabUser == null) {
            throw new ServiceException("用户在库中不存在");
        }
        // todo 这里需要判断redis中是否存在token

        UserApi userApi = gitlabServiceFactory.getInstance(instanceCode, UserApi.class);
        try {
            User user = userApi.getUser(gitlabUser.getGitlabUserId());
            if (null == user) {
                throw new ServiceException("gitlab用户不存在");
            }
            int result = userMapper.removeById(userId);
            userApi.deleteUser(user);
            return result;
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlab—api出现异常：" + e.getMessage());
        }

    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public int updateUser(UserUpdateDTO userUpdateDTO) {

        GitlabUser gitlabUser = userMapper.selectByPrimaryKey(userUpdateDTO.getId());
        if (gitlabUser == null) {
            throw new ServiceException("用户在库中不存在");
        }

        UserApi userApi = gitlabServiceFactory.getInstance(userUpdateDTO.getInstanceCode(), UserApi.class);

        try {
            User user = userApi.getUser(gitlabUser.getGitlabUserId());
            if (null == user) {
                throw new ServiceException("gitlab用户不存在");
            }

            // 这个配置会让用户登录时去修改密码
            userApi.updateUser(user, userUpdateDTO.getPwd());
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlab—api出现异常：" + e.getMessage());
        }
        if (userUpdateDTO.getPwd() != null) {
            gitlabUser.setPassword(stringEncryptor.decrypt(userUpdateDTO.getPwd()));
        }
        GitlabUserServiceConvert mapper = Mappers.getMapper(GitlabUserServiceConvert.class);
        GitlabUser gitlabUserResult = mapper.convert(userUpdateDTO);

        return userMapper.updateByPrimaryKeySelective(gitlabUserResult);

    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public int changeStatus(ChangeStatusDTO changeStatusDTO) {
        Long userId = changeStatusDTO.getId();

        GitlabUser gitlabUser = userMapper.selectByPrimaryKey(userId);

        if (gitlabUser == null) {
            throw new ServiceException("不存在该用户");
        }

        UserApi userApi = gitlabServiceFactory.getInstance(changeStatusDTO.getInstanceCode(), UserApi.class);

        try {
            if (changeStatusDTO.getStatus() == 1) {
                userApi.unblockUser(gitlabUser.getGitlabUserId());
            } else {
                userApi.blockUser(gitlabUser.getGitlabUserId());
            }
        } catch (GitLabApiException e) {
            throw new ServiceException("gitlabAPI出现异常" + e.getMessage());
        }
        gitlabUser.setStatus(changeStatusDTO.getStatus());
        gitlabUser.setUpdateBy(changeStatusDTO.getCreateBy());
        gitlabUser.setUpdateTime(new Date());
        return userMapper.updateByPrimaryKey(gitlabUser);
    }

    @Override
    public List<UserSelectOptionDTO> querySelectOption(String instanceCode) {
        return userMapper.querySelectOption(instanceCode);
    }

}
