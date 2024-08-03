package help.lixin.starlink.plugin.rocketmq.convert;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.rocketmq.api.dto.broker.BrokerDetailDTO;
import help.lixin.starlink.plugin.rocketmq.domain.RocketmqBrokerInfo;

@Mapper
public interface ClusterServiceConvert {

    RocketmqBrokerInfo convert(BrokerDetailDTO brokerDetailDTO);
}
