package help.lixin.core.engine.bridge.impl;

import help.lixin.core.definition.PipelineDefinition;
import help.lixin.core.engine.bridge.ITopicSubscribeFactory;

import java.time.Duration;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 这里要很小心,会有OOM的可能性,要用定时任务去清,或者做一个监听器,当工作流处理完成后,记得清除所有的信息.
 */
public class TopicSubscribeFaceService {

    private ITopicSubscribeFactory topicSubscribeFactory;

    // key     :  流水线key+version
    // value   :  流水线创建时间
    private final ConcurrentHashMap<String, Long> topicMark = new ConcurrentHashMap<>();

    // 准备订阅
    public void prepareSubscribe(PipelineDefinition pipelineDefinition) {
        String pipelineKey = pipelineDefinition.getKey();
        int version = 0;
        // pipelineKey + 版本号
        String key = String.format("%s:%d", pipelineKey, version);
        // 当前的秒
        long currentSecond = Duration.ofSeconds(System.currentTimeMillis()).getSeconds();
        Set<String> topicNames = pipelineDefinition.getPipelines().stream()
                //
                .filter(item -> {
                    return null != item.getPlugin();
                })
                //
                .map(item -> {
                    return item.getPlugin();
                })
                //
                .collect(Collectors.toSet());

        // key不存在的情况下,才会走函数体
        topicMark.computeIfAbsent(key, (i) -> {
            for (String topicName : topicNames) {
                topicSubscribeFactory.subscription(pipelineKey, topicName);
            }
            return currentSecond;
        });
    }
}
