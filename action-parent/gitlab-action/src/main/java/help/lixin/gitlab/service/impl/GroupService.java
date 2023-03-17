package help.lixin.gitlab.service.impl;

import help.lixin.gitlab.service.IGroupService;
import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.GroupApi;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.User;

import java.util.List;

public class GroupService implements IGroupService {

    private GroupApi groupApi;

    public GroupService(GroupApi groupApi) {
        this.groupApi = groupApi;
    }

    @Override
    public List<Group> all() throws GitLabApiException {
        return groupApi.getGroups();
    }

    @Override
    public Group addGroup(Group group) throws GitLabApiException {
        return groupApi.addGroup(group);
    }

    @Override
    public List<Group> query(String search) throws GitLabApiException {
        return groupApi.getGroups(search);
    }

    @Override
    public List<Member> getMembers(String groupIdOrPath) throws GitLabApiException {
        return groupApi.getMembers(groupIdOrPath);
    }

    @Override
    public Member addMember(User user, String groupIdOrPath, AccessLevel accessLevel) throws GitLabApiException {
        return groupApi.addMember(groupIdOrPath, user.getId(), accessLevel);
    }

    @Override
    public void removeMember(User user, String groupIdOrPath) throws GitLabApiException {
        groupApi.removeMember(groupIdOrPath, user.getId());
    }
}
