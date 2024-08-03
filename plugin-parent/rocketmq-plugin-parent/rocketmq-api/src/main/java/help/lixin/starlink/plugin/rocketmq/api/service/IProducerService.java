package help.lixin.starlink.plugin.rocketmq.api.service;

import help.lixin.starlink.plugin.rocketmq.api.dto.producer.ProducerInfoDTO;

import java.util.List;

public interface IProducerService {

    List<ProducerInfoDTO> producerConnection(String producerGroup, String topic);
}
