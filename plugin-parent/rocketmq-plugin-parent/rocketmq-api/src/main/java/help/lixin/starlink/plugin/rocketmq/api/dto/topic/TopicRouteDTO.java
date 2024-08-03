package help.lixin.starlink.plugin.rocketmq.api.dto.topic;

import help.lixin.starlink.plugin.rocketmq.api.dto.broker.BrokerInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.queue.QueueInfoDTO;

import java.util.List;
import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 4:52 下午
 * @Description
 */
public class TopicRouteDTO {

    private String orderTopicConf;

    private List<QueueInfoDTO> queueDatas;

    private List<BrokerInfoDTO> brokerDatas;

    private Map<String,String> filterServerTable;

    public String getOrderTopicConf() {
        return orderTopicConf;
    }

    public void setOrderTopicConf(String orderTopicConf) {
        this.orderTopicConf = orderTopicConf;
    }

    public List<QueueInfoDTO> getQueueDatas() {
        return queueDatas;
    }

    public void setQueueDatas(List<QueueInfoDTO> queueDatas) {
        this.queueDatas = queueDatas;
    }

    public List<BrokerInfoDTO> getBrokerDatas() {
        return brokerDatas;
    }

    public void setBrokerDatas(List<BrokerInfoDTO> brokerDatas) {
        this.brokerDatas = brokerDatas;
    }

    public Map<String, String> getFilterServerTable() {
        return filterServerTable;
    }

    public void setFilterServerTable(Map<String, String> filterServerTable) {
        this.filterServerTable = filterServerTable;
    }
}
