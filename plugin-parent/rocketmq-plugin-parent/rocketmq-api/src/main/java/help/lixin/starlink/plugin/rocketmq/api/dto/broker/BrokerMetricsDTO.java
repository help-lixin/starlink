package help.lixin.starlink.plugin.rocketmq.api.dto.broker;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/24 5:15 下午
 * @Description
 */
public class BrokerMetricsDTO {

    private String msgPutTotalTodayNow;
    private String commitLogDiskRatio;

    public String getMsgPutTotalTodayNow() {
        return msgPutTotalTodayNow;
    }

    public void setMsgPutTotalTodayNow(String msgPutTotalTodayNow) {
        this.msgPutTotalTodayNow = msgPutTotalTodayNow;
    }

    public String getCommitLogDiskRatio() {
        return commitLogDiskRatio;
    }

    public void setCommitLogDiskRatio(String commitLogDiskRatio) {
        this.commitLogDiskRatio = commitLogDiskRatio;
    }
}
