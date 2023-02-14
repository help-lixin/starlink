package help.lixin;

import org.gitlab4j.api.models.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class GroupTest extends BasicTest {

    @Test
    public void testGroupList() throws Exception {
        List<Group> groups = gitLabApi.getGroupApi().getGroups();
        Assert.assertNotNull(groups);
    }

    /**
     * 添加组
     *
     * @throws Exception
     */
    @Test
    public void testAddGroup() throws Exception {
        Group group = new Group();
        group.setName("order group");
        group.setPath("order-group");
        group.setVisibility(Visibility.PRIVATE);
        group.setRequestAccessEnabled(Boolean.TRUE);
        gitLabApi.getGroupApi().addGroup(group);
    }

    /**
     * 条件检索出所有的组
     *
     * @throws Exception
     */
    @Test
    public void testGroups() throws Exception {
        // 根据name进行检索
        List<Group> orderGroups = gitLabApi.getGroupApi().getGroups("order group");
        Assert.assertNotNull(orderGroups);
    }

    /**
     * 查看group下有哪些成员
     *
     * @throws Exception
     */
    @Test
    public void testMembers() throws Exception {
        List<Member> members = gitLabApi.getGroupApi().getMembers("order-group");
        Assert.assertNotNull(members);
    }

    @Test
    public void testGroupAddMember() throws Exception {
        User zhgnsan = gitLabApi.getUserApi().getUser("zhangsan");
        gitLabApi.getGroupApi().addMember("order-group", zhgnsan.getId(), AccessLevel.DEVELOPER);

//        gitLabApi.getGroupApi().removeMember();
    }
}
