package help.lixin.gitlab.action;

public class GitlabActionParams {
    private String projectName;
    private String branch;
    private String url;

    public String getProjectName() {
        if (null == projectName && null != url) {
            autoConfigProjectName();
        }
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
        if (null != url) {
            autoConfigProjectName();
        }
    }

    private void autoConfigProjectName() {
        int startIndex = url.lastIndexOf("/");
        int endIndex = url.lastIndexOf(".");
        this.projectName = url.substring(startIndex + 1, endIndex);
    }
}
