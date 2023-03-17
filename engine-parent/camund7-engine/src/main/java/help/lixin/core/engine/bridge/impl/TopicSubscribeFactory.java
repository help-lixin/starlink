package help.lixin.core.engine.bridge.impl;

import help.lixin.core.engine.bridge.ITopicSubscribeCustomizer;
import help.lixin.core.engine.bridge.ITopicSubscribeFactory;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.topic.TopicSubscription;
import org.camunda.bpm.client.topic.TopicSubscriptionBuilder;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class TopicSubscribeFactory implements ITopicSubscribeFactory, EnvironmentAware, DisposableBean {
    private ExternalTaskClient client;

    private Environment environment;

    private List<ITopicSubscribeCustomizer> customizers = new ArrayList<>(0);

    public TopicSubscribeFactory(ExternalTaskClient camundaClient,
                                 //
                                 List<ITopicSubscribeCustomizer> customizers) {
        this.client = camundaClient;
        if (!customizers.isEmpty()) {
            this.customizers = customizers;
        }
    }

    @Override
    public TopicSubscription subscription(String pipelineKey,
                                          // 优定优于配置(topicName直接就是plugin名称)
                                          String topicName,
                                          //
                                          Long duration) {
        // 针对duration处理
        if (null == duration) {
            // 举例:
            // jenkins.duration
            // gitlab.duration
            // harbor.duration
            // k8s-deploy.duration
            String propertyName = String.format("%s.duration", topicName);
            // 以秒为单位(不配置,默认就是:60秒,即:1分钟)
            long durationSecond = environment.getProperty(propertyName, Long.class, 60L);
            // 将分钟转换成毫秒
            duration = Duration.ofSeconds(durationSecond).toMillis();
        }

        TopicSubscriptionBuilder topicSubscriptionBuilder = client.subscribe(topicName)
                //
                .processDefinitionKey(pipelineKey)
                //
                .lockDuration(duration);
        // customizer
        for (ITopicSubscribeCustomizer customizer : customizers) {
            customizer.customizer(topicSubscriptionBuilder);
        }
        return topicSubscriptionBuilder.open();
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void destroy() throws Exception {
        this.environment = null;
        this.client = null;
    }
}
