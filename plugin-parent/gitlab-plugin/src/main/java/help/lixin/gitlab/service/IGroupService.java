package help.lixin.gitlab.service;

import org.gitlab4j.api.GitLabApiException;
import org.gitlab4j.api.models.AccessLevel;
import org.gitlab4j.api.models.Group;
import org.gitlab4j.api.models.Member;
import org.gitlab4j.api.models.User;

import java.util.List;

public interface IGroupService {

    List<Group> all() throws GitLabApiException;

    Group addGroup(Group group) throws GitLabApiException;

    List<Group> query(String search) throws GitLabApiException;

    List<Member> getMembers(String groupIdOrPath) throws GitLabApiException;

    Member addMember(User user, String groupIdOrPath, AccessLevel accessLevel) throws GitLabApiException;

    void removeMember(User user, String groupIdOrPath) throws GitLabApiException;
}
