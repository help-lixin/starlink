package help.lixin.starlink.plugin.jsch.action.domain.copy;

import java.util.HashSet;
import java.util.Set;

import help.lixin.starlink.plugin.jsch.action.domain.command.ExecuteStrategy;
import help.lixin.starlink.plugin.jsch.action.domain.command.ExecuteSuccessStrategy;

public class SSHDeployActionParams {
    private Set<String> labels = new HashSet<>(0);
    // 本地目录(可以指定包含/排除哪些文件)
    private LocalDir localDir;
    // 绝对文件
    private String localFile;
    // 远程主机的目录
    private String remoteDir;
    // 拷贝文件之前执行命令
    protected String beforeCommand;
    // 拷贝文件之后执行的命令
    protected String afterCommand;
    // 执行成功策略(只要有一个在成功则成功?还是,要求所有的成功才是成功)
    private ExecuteSuccessStrategy executeSuccessStrategy = ExecuteSuccessStrategy.LEAST_ONCE;
    // 执行策略(串行/并行)
    private ExecuteStrategy executeStrategy = ExecuteStrategy.SERIAL;

    // 执行最大等待时间(以分钟为单位)
    private long executeMaxWaitTime = 10;

    public Set<String> getLabels() {
        return labels;
    }

    public void setLabels(Set<String> labels) {
        this.labels = labels;
    }

    public LocalDir getLocalDir() {
        return localDir;
    }

    public void setLocalDir(LocalDir localDir) {
        this.localDir = localDir;
    }

    public String getLocalFile() {
        return localFile;
    }

    public void setLocalFile(String localFile) {
        this.localFile = localFile;
    }

    public String getRemoteDir() {
        return remoteDir;
    }

    public void setRemoteDir(String remoteDir) {
        this.remoteDir = remoteDir;
    }

    public String getBeforeCommand() {
        return beforeCommand;
    }

    public void setBeforeCommand(String beforeCommand) {
        this.beforeCommand = beforeCommand;
    }

    public String getAfterCommand() {
        return afterCommand;
    }

    public void setAfterCommand(String afterCommand) {
        this.afterCommand = afterCommand;
    }

    public ExecuteSuccessStrategy getExecuteSuccessStrategy() {
        return executeSuccessStrategy;
    }

    public void setExecuteSuccessStrategy(ExecuteSuccessStrategy executeSuccessStrategy) {
        this.executeSuccessStrategy = executeSuccessStrategy;
    }

    public ExecuteStrategy getExecuteStrategy() {
        return executeStrategy;
    }

    public void setExecuteStrategy(ExecuteStrategy executeStrategy) {
        this.executeStrategy = executeStrategy;
    }

    public long getExecuteMaxWaitTime() {
        return executeMaxWaitTime;
    }

    public void setExecuteMaxWaitTime(long executeMaxWaitTime) {
        this.executeMaxWaitTime = executeMaxWaitTime;
    }
}
