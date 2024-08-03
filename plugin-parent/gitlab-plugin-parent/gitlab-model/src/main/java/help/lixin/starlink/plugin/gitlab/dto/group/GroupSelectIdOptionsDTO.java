package help.lixin.starlink.plugin.gitlab.dto.group;

/**
 * @Author: 伍岳林
 * @Date: 2023/11/30 4:46 下午
 * @Description
 */
public class GroupSelectIdOptionsDTO {

    private String gitlabGroupName;

    private Long id;

    public String getGitlabGroupName() {
        return gitlabGroupName;
    }

    public void setGitlabGroupName(String gitlabGroupName) {
        this.gitlabGroupName = gitlabGroupName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
