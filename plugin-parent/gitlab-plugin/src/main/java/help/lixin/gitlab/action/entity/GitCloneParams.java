package help.lixin.gitlab.action.entity;

public class GitCloneParams {
    // URL信息:
    // ssh://git@192.168.8.10:2222/erp/spring-web-demo.git
    // http://192.168.8.10/erp/spring-web-demo.git
    private String url;
    // 项目名称:spring-web-demo
    private String projectName;
    // 分支:main
    private String branch;
    // git认证方式
    private CredentialsProvider credential = new TokenCredentialsProvider();
    // clone后的源码工作空间目录
    private String workspaceDir = "/tmp";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProjectName() {
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

    public CredentialsProvider getCredential() {
        return credential;
    }

    public void setCredential(CredentialsProvider credential) {
        this.credential = credential;
    }

    public String getWorkspaceDir() {
        return workspaceDir;
    }

    public void setWorkspaceDir(String workspaceDir) {
        this.workspaceDir = workspaceDir;
    }

    @Override
    public String toString() {
        return "GitCloneParams{" +
                "url='" + url + '\'' +
                ", projectName='" + projectName + '\'' +
                ", branch='" + branch + '\'' +
                ", credential=" + credential +
                ", workspaceDir='" + workspaceDir + '\'' +
                '}';
    }
}
