package help.lixin.starlink.plugin.jsch.action.domain.command;

import java.util.HashSet;
import java.util.Set;

public class CmdParams {
    // 要针对哪些机器进行操作
    private Set<String> labels = new HashSet<>(0);
    // 执行命令
    private String command;
    /**
     * 负载均衡策略
     */
    private LoadBalance loadBalance = LoadBalance.RANDOM;

    // 执行最大等待时间(以分钟为单位)
    private long executeMaxWaitTime = 60;

    public Set<String> getLabels() {
        return labels;
    }

    public void setLabels(Set<String> labels) {
        this.labels = labels;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
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
}
