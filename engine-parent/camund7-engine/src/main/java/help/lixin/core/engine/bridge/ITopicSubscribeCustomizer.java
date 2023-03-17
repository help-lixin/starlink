package help.lixin.core.engine.bridge;

import org.camunda.bpm.client.topic.TopicSubscriptionBuilder;

public interface ITopicSubscribeCustomizer {
    void customizer(TopicSubscriptionBuilder topicSubscriptionBuilder);
}
