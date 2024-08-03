package help.lixin.starlink.plugin.jenkins.dto.systool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class JenkinsConfigDTO {
    @JsonProperty("jenkins-mvn-GlobalMavenConfig")
    private GlobalMavenConfigDTO globalMavenConfig;

    @JsonProperty("hudson-model-JDK")
    private JDKDTO jdkDTO;

    @JsonProperty("hudson-plugins-git-GitTool")
    private GitDTO gitDTO;

    @JsonProperty("hudson-plugins-gradle-GradleInstallation")
    private GradleDTO gradleDTO;

    @JsonProperty("hudson-tasks-Ant$AntInstallation")
    private AntDTO antDTO;

    @JsonProperty("hudson-tasks-Maven$MavenInstallation")
    private MavenDTO mavenDTO;

    @JsonProperty("Submit")
    private String submit = "";

    @JsonProperty("core:apply")
    private String coreApply="";
//
//    @JsonProperty("Jenkins-Crumb")
//    private String jenkinsCrumb;


    @JsonProperty("jenkins-plugins-nodejs-tools-NodeJSInstallation")
    private NodejsDTO nodejsDTO;

    @JsonProperty("org-jenkinsci-plugins-golang-GolangInstallation")
    private GoDTO goDTO;

    public GlobalMavenConfigDTO getGlobalMavenConfig() {
        return globalMavenConfig;
    }

    public void setGlobalMavenConfig(GlobalMavenConfigDTO globalMavenConfig) {
        this.globalMavenConfig = globalMavenConfig;
    }

    public JDKDTO getJdkDTO() {
        return jdkDTO;
    }

    public void setJdkDTO(JDKDTO jdkDTO) {
        this.jdkDTO = jdkDTO;
    }

    public GitDTO getGitDTO() {
        return gitDTO;
    }

    public void setGitDTO(GitDTO gitDTO) {
        this.gitDTO = gitDTO;
    }

    public GradleDTO getGradleDTO() {
        return gradleDTO;
    }

    public void setGradleDTO(GradleDTO gradleDTO) {
        this.gradleDTO = gradleDTO;
    }


    public MavenDTO getMavenDTO() {
        return mavenDTO;
    }

    public void setMavenDTO(MavenDTO mavenDTO) {
        this.mavenDTO = mavenDTO;
    }

    public AntDTO getAntDTO() {
        return antDTO;
    }

    public void setAntDTO(AntDTO antDTO) {
        this.antDTO = antDTO;
    }

    public NodejsDTO getNodejsDTO() {
        return nodejsDTO;
    }

    public void setNodejsDTO(NodejsDTO nodejsDTO) {
        this.nodejsDTO = nodejsDTO;
    }

    public GoDTO getGoDTO() {
        return goDTO;
    }

    public void setGoDTO(GoDTO goDTO) {
        this.goDTO = goDTO;
    }

    public String getSubmit() {
        return submit;
    }

    public void setSubmit(String submit) {
        this.submit = submit;
    }

    public String getCoreApply() {
        return coreApply;
    }

    public void setCoreApply(String coreApply) {
        this.coreApply = coreApply;
    }
}