package help.lixin.jenkins.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JenkinsActionParams {

    // 模板文件id(来自于DB存的ID)
    private String templateId;
    // 模板文件位置
    private String templateFile;
    // 凭证id
    private String credentialId;
    // 编译环境(java/nodejs/gradle)
    private String compile;
    // 成品位置
    private String archiveArtifacts;
    // Dockerfile模板名称(Docker会通过DB统一存储)
    private String dockerFile;
    // jenkins中的stage
    private List<JenkinsStage> stages = new ArrayList<>();

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

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public List<JenkinsStage> getStages() {
        return stages;
    }

    public void setStages(List<JenkinsStage> stages) {
        this.stages = stages;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
