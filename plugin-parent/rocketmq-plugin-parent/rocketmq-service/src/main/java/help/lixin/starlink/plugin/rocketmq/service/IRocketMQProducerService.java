package help.lixin.starlink.plugin.rocketmq.service;

import java.util.List;

import help.lixin.starlink.plugin.rocketmq.api.dto.producer.ProducerInfoDTO;

public interface IRocketMQProducerService {

    List<ProducerInfoDTO> producerConnection(String producerGroup, String topic, String instanceName);
}
