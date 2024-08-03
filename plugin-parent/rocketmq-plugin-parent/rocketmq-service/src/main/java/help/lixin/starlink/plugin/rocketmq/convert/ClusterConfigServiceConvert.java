package help.lixin.starlink.plugin.rocketmq.convert;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.rocketmq.api.dto.cluster.ClusterConfigInfoDTO;
import help.lixin.starlink.plugin.rocketmq.domain.RocketmqClusterConfig;

/**
 * @Author: 伍岳林
 * @Date: 2023/9/13 3:11 下午
 * @Description
 */
@Mapper
public interface ClusterConfigServiceConvert {

    RocketmqClusterConfig convert(ClusterConfigInfoDTO clusterConfigInfoDTO);
}
