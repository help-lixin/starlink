package help.lixin.gitlab.service;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.User;

import java.util.List;

public interface IUserService {
    List<User> all() throws GitLabApiException;

    User createUser(User user, String pwd) throws GitLabApiException;

    void deleteUser(String userName) throws GitLabApiException;

    Boolean updateUser(String userName, String newPwd) throws GitLabApiException;
}
