package help.lixin.starlink.plugin.svn.action.entity;

public class SvnCheckoutParams {
    // svn://192.168.8.16/spring_web_demo
    private String url;
    // 项目名称:spring-web-demo
    private String projectName;
    // 凭证id
    private String credentialId;

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

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

}