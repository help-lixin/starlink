package help.lixin.gitlab.api;

import org.gitlab4j.api.models.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UserTest extends BasicTest {

    @Test
    public void testGetUsers() throws Exception {
        List<User> users = gitLabApi.getUserApi().getUsers();
        Assert.assertNotNull(users);
    }


    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        user.setName("张三");
        user.setUsername("zhangsan");
        user.setEmail("278904627@qq.com");
        // 第一次登录时,是否跳过确认修改密码阶段
        user.setSkipConfirmation(Boolean.TRUE);
        // 能否创建组
        user.setCanCreateGroup(Boolean.TRUE);
        // 能否创建项目
        user.setCanCreateProject(Boolean.TRUE);
        user.setIsAdmin(Boolean.FALSE);
        User restUser = gitLabApi.getUserApi().createUser(user, "Li@88888888", Boolean.FALSE);
        Assert.assertNotNull(restUser);
    }

    @Test
    public void testDeleteUser() throws Exception {
        User zhangsan = gitLabApi.getUserApi().getUser("zhangsan");
        gitLabApi.getUserApi().deleteUser(zhangsan.getId(), Boolean.TRUE);
    }

    @Test
    public void testUpdateUser() throws Exception {
        User zhangsan = gitLabApi.getUserApi().getUser("zhangsan");
        // 这个配置会让用户登录时去修改密码
        User user = gitLabApi.getUserApi().updateUser(zhangsan, "Li@88888888");
        Assert.assertNotNull(user);
    }
}
