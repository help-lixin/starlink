package help.lixin.k8s.action;

import help.lixin.k8s.constants.Constant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DeploymentParams implements Serializable {
    // 可以指定deploy YAML文件来自于界面配置的id(1),为什么是字符串?方便进行转换.
    private String deployId;
    // 指定具体的deploy yaml位置(例如:/tmp/deploy.yml)
    private String yamlTemplatePath;

    // 命名空间
    private String namespace = Constant.DEFAULT_NAMESPACE;
    // 部署的名称
    private String deployName;


    // pod的标签信息
    private String podLabelName;
    private String podLabelValue;
    // 指定镜像拉取密钥
    private String imagePullSecretName;
    // 镜像名称
    private String image;
    // pod名称
    private String containerName;
    // cluster ip export port
    private String port;
    // env
    private List<DeploymentEnv> envs = new ArrayList<>();
    // labels
    private List<Label> labels = new ArrayList<>();

    // 自定义变量
    private List<DeploymentVar> vars = new ArrayList<>();

    public String getDeployId() {
        return deployId;
    }

    public void setDeployId(String deployId) {
        this.deployId = deployId;
    }

    public String getYamlTemplatePath() {
        return yamlTemplatePath;
    }

    public void setYamlTemplatePath(String yamlTemplatePath) {
        this.yamlTemplatePath = yamlTemplatePath;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getDeployName() {
        return deployName;
    }

    public void setDeployName(String deployName) {
        this.deployName = deployName;
    }

    public String getPodLabelName() {
        return podLabelName;
    }

    public void setPodLabelName(String podLabelName) {
        this.podLabelName = podLabelName;
    }

    public String getPodLabelValue() {
        return podLabelValue;
    }

    public void setPodLabelValue(String podLabelValue) {
        this.podLabelValue = podLabelValue;
    }

    public String getImagePullSecretName() {
        return imagePullSecretName;
    }

    public void setImagePullSecretName(String imagePullSecretName) {
        this.imagePullSecretName = imagePullSecretName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContainerName() {
        return containerName;
    }

    public void setContainerName(String containerName) {
        this.containerName = containerName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public List<DeploymentEnv> getEnvs() {
        return envs;
    }

    public void setEnvs(List<DeploymentEnv> envs) {
        this.envs = envs;
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void setLabels(List<Label> labels) {
        this.labels = labels;
    }

    public List<DeploymentVar> getVars() {
        return vars;
    }

    public void setVars(List<DeploymentVar> vars) {
        this.vars = vars;
    }
}