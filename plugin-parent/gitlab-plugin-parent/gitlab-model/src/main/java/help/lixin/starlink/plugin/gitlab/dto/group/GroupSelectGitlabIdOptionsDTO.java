package help.lixin.starlink.plugin.gitlab.dto.group;

/**
 * @Author: 伍岳林
 * @Date: 2023/11/30 4:46 下午
 * @Description
 */
public class GroupSelectGitlabIdOptionsDTO {

    private String gitlabGroupName;

    private Long gitlabGroupId;

    public String getGitlabGroupName() {
        return gitlabGroupName;
    }

    public void setGitlabGroupName(String gitlabGroupName) {
        this.gitlabGroupName = gitlabGroupName;
    }

    public Long getGitlabGroupId() {
        return gitlabGroupId;
    }

    public void setGitlabGroupId(Long gitlabGroupId) {
        this.gitlabGroupId = gitlabGroupId;
    }
}
