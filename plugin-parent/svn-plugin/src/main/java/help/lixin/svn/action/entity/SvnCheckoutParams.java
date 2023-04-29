package help.lixin.svn.action.entity;


public class SvnCheckoutParams {
    // svn://192.168.8.16/spring_web_demo
    private String url;
    // 项目名称:spring-web-demo
    private String projectName;
    // 用户名
    private String userName;
    // 密码
    private String password;
    // 工作目录
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWorkspaceDir() {
        return workspaceDir;
    }

    public void setWorkspaceDir(String workspaceDir) {
        this.workspaceDir = workspaceDir;
    }

    @Override
    public String toString() {
        return "SvnCheckoutParams{" +
                "url='" + url + '\'' +
                ", projectName='" + projectName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", workspaceDir='" + workspaceDir + '\'' +
                '}';
    }
}
