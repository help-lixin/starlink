package help.lixin.starlink.plugin.rocketmq.convert;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.rocketmq.api.dto.consumer.ConsumerDeleteDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.consumer.ConsumerSubscriptionGroupInfoDTO;
import help.lixin.starlink.plugin.rocketmq.request.consumer.ConsumerDeleteRequest;
import help.lixin.starlink.plugin.rocketmq.request.consumer.ConsumerSubscriptionGroupInfoRequest;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 5:40 下午
 * @Description
 */
@Mapper
public interface ConsumerConvert {

    ConsumerSubscriptionGroupInfoDTO convert(ConsumerSubscriptionGroupInfoRequest consumerSubscriptionGroupInfoRequest);

    ConsumerDeleteDTO convert(ConsumerDeleteRequest consumerDeleteRequest);
}
