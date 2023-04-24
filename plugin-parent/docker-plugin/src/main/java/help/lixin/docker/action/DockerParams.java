package help.lixin.docker.action;

import java.util.ArrayList;
import java.util.List;

public class DockerParams {
    // 成品库目录
    private String arfifactDir;
    // 成品库生成后的文件名
    private String arfifactName;
    // Dockerfile
    private String dockerFile;
    // args
    private List<DockerBuildArg> args = new ArrayList<>();
    // 用逗号分隔多个标签
    private String tags;
    // 是否自动构建
    private boolean isAutoBuild = true;
    // 是否自动推送
    private boolean isAutoPush = true;

    public String getDockerFile() {
        return dockerFile;
    }

    public void setDockerFile(String dockerFile) {
        this.dockerFile = dockerFile;
    }

    public List<DockerBuildArg> getArgs() {
        return args;
    }

    public void setArgs(List<DockerBuildArg> args) {
        this.args = args;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isAutoBuild() {
        return isAutoBuild;
    }

    public void setAutoBuild(boolean autoBuild) {
        isAutoBuild = autoBuild;
    }

    public boolean isAutoPush() {
        return isAutoPush;
    }

    public void setAutoPush(boolean autoPush) {
        isAutoPush = autoPush;
    }

    public String getArfifactDir() {
        return arfifactDir;
    }

    public void setArfifactDir(String arfifactDir) {
        this.arfifactDir = arfifactDir;
    }

    public String getArfifactName() {
        return arfifactName;
    }

    public void setArfifactName(String arfifactName) {
        this.arfifactName = arfifactName;
    }
}
