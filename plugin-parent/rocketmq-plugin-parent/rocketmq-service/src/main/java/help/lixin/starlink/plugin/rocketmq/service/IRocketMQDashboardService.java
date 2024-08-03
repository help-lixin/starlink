package help.lixin.starlink.plugin.rocketmq.service;

import java.util.List;
import java.util.Map;

public interface IRocketMQDashboardService {

    List<String> topicCurrent(String instanceName);

    Map<String, List<String>> queryBroker(String queryDate, String instanceName);

    List<String> queryTopic(String queryDate, String topicName, String instanceName);
}
