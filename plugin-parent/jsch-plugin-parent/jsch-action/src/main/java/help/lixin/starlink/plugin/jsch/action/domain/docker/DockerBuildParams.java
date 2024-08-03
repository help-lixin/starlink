package help.lixin.starlink.plugin.jsch.action.domain.docker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import help.lixin.starlink.core.constants.Constant;
import help.lixin.starlink.plugin.jsch.action.domain.command.LoadBalance;

public class DockerBuildParams implements Serializable {
    /**
     * 在哪台机器进行业务操作
     */
    private Set<String> labels = new HashSet<>(0);

    /**
     * 工作目录(#{REPOSITORY_WORKSPACE})
     */
    private String workDir = String.format("#{%s}", Constant.SourceCodeRepository.REPOSITORY_WORKSPACE);

    /**
     * Dockerfile位置(#{REPOSITORY_WORKSPACE}/Dockerfile)
     */
    private String dockerFile = String.format("#{%s}/Dockerfile", Constant.SourceCodeRepository.REPOSITORY_WORKSPACE);

    /**
     * #{REPOSITORY_WORKSPACE}/target/ Docker上下文,在执行docker build时,Docker会把Dockerfile和上下文一起打包,让Docker
     * Daemo进行执行,产生镜像出来,建议上下文的内容比较少一些,否则,需要很长的时间压缩传递给Docker Daemo
     */
    private String buildContext = ".";

    /**
     * 最终镜像名称( #{IMAGE_REPOSITORY}/#{IMAGE_PROJECT}/spring-web-demo:#{BUILD_NUMBER} )
     */
    private String targetImage = String.format("#{%s}/#{%s}/xxx-xxx:#{%s}", //
        Constant.ImageRepository.IMAGE_REPOSITORY, //
        Constant.ImageRepository.IMAGE_PROJECT, //
        Constant.BuildInfo.BUILD_NUMBER);
    /**
     * 构建参数
     */
    private List<DockerBuildArgItem> args = new ArrayList<>(0);

    /**
     * 负载均衡策略
     */
    private LoadBalance loadBalance = LoadBalance.RANDOM;
    /**
     * 执行最大等待时间(以分钟为单位)
     */
    private long executeMaxWaitTime = 60;

    /**
     * 是否开启推送
     */
    private boolean isPush = Boolean.TRUE;

    public Set<String> getLabels() {
        return labels;
    }

    public void setLabels(Set<String> labels) {
        this.labels = labels;
    }

    public String getWorkDir() {
        return workDir;
    }

    public void setWorkDir(String workDir) {
        this.workDir = workDir;
    }

    public String getDockerFile() {
        return dockerFile;
    }

    public void setDockerFile(String dockerFile) {
        this.dockerFile = dockerFile;
    }

    public String getTargetImage() {
        return targetImage;
    }

    public void setTargetImage(String targetImage) {
        this.targetImage = targetImage;
    }

    public List<DockerBuildArgItem> getArgs() {
        return args;
    }

    public void setArgs(List<DockerBuildArgItem> args) {
        this.args = args;
    }

    public LoadBalance getLoadBalance() {
        return loadBalance;
    }

    public void setLoadBalance(LoadBalance loadBalance) {
        this.loadBalance = loadBalance;
    }

    public long getExecuteMaxWaitTime() {
        return executeMaxWaitTime;
    }

    public void setExecuteMaxWaitTime(long executeMaxWaitTime) {
        this.executeMaxWaitTime = executeMaxWaitTime;
    }

    public String getBuildContext() {
        return buildContext;
    }

    public void setBuildContext(String buildContext) {
        this.buildContext = buildContext;
    }

    public boolean isPush() {
        return isPush;
    }

    public void setPush(boolean push) {
        isPush = push;
    }
}