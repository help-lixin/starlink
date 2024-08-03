package help.lixin.starlink.plugin.rocketmq.api.service;

import java.util.List;
import java.util.Map;

public interface IDashboardService {

    List<String> topicCurrent();

    Map<String, List<String>> queryBroker(String queryDate);

    List<String> queryTopic(String queryDate,String topicName);


}
