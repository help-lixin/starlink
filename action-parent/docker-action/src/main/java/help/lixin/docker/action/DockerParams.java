package help.lixin.docker.action;

import java.util.ArrayList;
import java.util.List;

public class DockerParams {
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
}
