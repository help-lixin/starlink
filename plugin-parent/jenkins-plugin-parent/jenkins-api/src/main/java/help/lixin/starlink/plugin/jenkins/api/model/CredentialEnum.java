package help.lixin.starlink.plugin.jenkins.api.model;

public enum CredentialEnum {
    USERNAME_PASSWORD(
        "/manage/descriptorByName/com.cloudbees.plugins.credentials.impl.UsernamePasswordCredentialsImpl/checkId"),
    TOKEN("/manage/descriptorByName/com.dabsquared.gitlabjenkins.connection.GitLabApiTokenImpl/checkId"),
    SSH("/manage/descriptorByName/com.cloudbees.jenkins.plugins.sshcredentials.impl.BasicSSHUserPrivateKey/checkId"),
    FILE("/manage/descriptorByName/org.jenkinsci.plugins.plaincredentials.impl.FileCredentialsImpl/checkId"),
    TEXT("/manage/descriptorByName/org.jenkinsci.plugins.plaincredentials.impl.StringCredentialsImpl/checkId"),
    CERTIFICATE("/manage/descriptorByName/com.cloudbees.plugins.credentials.impl.CertificateCredentialsImpl/checkId"),
    GIT("/descriptorByName/hudson.plugins.git.UserRemoteConfig/checkCredentialsId");

    private String description;

    private CredentialEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
