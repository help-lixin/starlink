package help.lixin.core.engine.bridge;


import org.camunda.bpm.client.topic.TopicSubscription;

public interface ITopicSubscribeFactory {

    default TopicSubscription subscription(String pipelineKey, String topicName) {
        return subscription(pipelineKey, topicName, null);
    }

    TopicSubscription subscription(String pipelineKey, String topicName, Long duration);
}
