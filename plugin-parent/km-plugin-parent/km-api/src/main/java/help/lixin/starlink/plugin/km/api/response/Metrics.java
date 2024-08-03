package help.lixin.starlink.plugin.km.api.response;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/15 7:22 下午
 * @Description
 */
public class Metrics extends BaseMetrics {
    private String  topic;

    private Integer brokerId;

    /**
     * topic的指标是否是有broker上的指标聚合而来，true的时候brokerId为null
     */
    private boolean bBrokerAgg = true;

    /**
     * brokerAgg: 1：是由broker聚合而成的topic整体维度指标，0：是broker维度的指标
     * 针对类似枚举和状态类属性，使用字符串来表示，ES的查询效率更高
     */
    private String brokerAgg = "1";

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getBrokerId() {
        return brokerId;
    }

    public void setBrokerId(Integer brokerId) {
        this.brokerId = brokerId;
    }

    public boolean getbbrokerAgg() {
        return bBrokerAgg;
    }

    public void setbbrokerAgg(boolean bBrokerAgg) {
        this.bBrokerAgg = bBrokerAgg;
    }

    public String getBrokerAgg() {
        return brokerAgg;
    }

    public void setBrokerAgg(String brokerAgg) {
        this.brokerAgg = brokerAgg;
    }
}
