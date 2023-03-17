package help.lixin.gitlab.service.impl;

import help.lixin.gitlab.service.IUserService;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.UserApi;
import org.gitlab4j.api.models.User;

import java.util.List;

public class UserService implements IUserService {
    private UserApi userApi;

    public UserService(UserApi userApi) {
        this.userApi = userApi;
    }

    @Override
    public List<User> all() throws GitLabApiException {
        return userApi.getUsers();
    }

    @Override
    public User createUser(User user, String pwd) throws GitLabApiException {
        return userApi.createUser(user, pwd, Boolean.FALSE);
    }

    @Override
    public void deleteUser(String userName) throws GitLabApiException {
        User user = userApi.getUser(userName);
        if (null != user) {
            userApi.deleteUser(user);
        }
    }

    @Override
    public Boolean updateUser(String userName, String newPwd) throws GitLabApiException {
        User user = userApi.getUser(userName);
        if (null != user) {
            // 这个配置会让用户登录时去修改密码
            userApi.updateUser(user, newPwd);
            return true;
        }
        return false;
    }
}
