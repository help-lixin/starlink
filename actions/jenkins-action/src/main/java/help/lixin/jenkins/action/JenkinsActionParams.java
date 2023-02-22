package help.lixin.jenkins.action;

import java.util.HashMap;
import java.util.Map;

public class JenkinsActionParams {
    // 模板文件位置
    private String templateFile;
    // 凭证id
    private String credentialId;
    // 编译环境(java/nodejs/gradle)
    private String compile;
    // 要执行的命令
    private String cmd;
    // 成品位置
    private String archiveArtifacts;
    // Dockerfile模板名称(Docker会通过DB统一存储)
    private String dockerFile;

    // 其它信息
    private Map<String, Object> others = new HashMap<>();

    public String getTemplateFile() {
        return templateFile;
    }

    public void setTemplateFile(String templateFile) {
        this.templateFile = templateFile;
    }

    public String getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(String credentialId) {
        this.credentialId = credentialId;
    }

    public String getCompile() {
        return compile;
    }

    public void setCompile(String compile) {
        this.compile = compile;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getArchiveArtifacts() {
        return archiveArtifacts;
    }

    public void setArchiveArtifacts(String archiveArtifacts) {
        this.archiveArtifacts = archiveArtifacts;
    }

    public Map<String, Object> getOthers() {
        return others;
    }

    public void setOthers(Map<String, Object> others) {
        this.others = others;
    }

    public String getDockerFile() {
        return dockerFile;
    }

    public void setDockerFile(String dockerFile) {
        this.dockerFile = dockerFile;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
