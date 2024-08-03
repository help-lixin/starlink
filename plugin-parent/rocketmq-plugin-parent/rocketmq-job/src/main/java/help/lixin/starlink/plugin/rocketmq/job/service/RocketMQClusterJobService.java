package help.lixin.starlink.plugin.rocketmq.job.service;

import java.util.Map;
import java.util.Set;

import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.rocketmq.api.dto.broker.BrokerDetailDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.broker.BrokerInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.broker.BrokerServerDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.cluster.ClusterConfigInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.service.IClusterService;
import help.lixin.starlink.plugin.rocketmq.convert.ClusterConfigServiceConvert;
import help.lixin.starlink.plugin.rocketmq.convert.ClusterServiceConvert;
import help.lixin.starlink.plugin.rocketmq.domain.RocketmqBrokerInfo;
import help.lixin.starlink.plugin.rocketmq.domain.RocketmqClusterConfig;
import help.lixin.starlink.plugin.rocketmq.domain.RocketmqClusterInfo;
import help.lixin.starlink.plugin.rocketmq.mapper.RocketmqBrokerInfoMapper;
import help.lixin.starlink.plugin.rocketmq.mapper.RocketmqClusterConfigMapper;
import help.lixin.starlink.plugin.rocketmq.mapper.RocketmqClusterInfoMapper;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/26 9:50 下午
 * @Description 查询gitlab的project对比数据库的数据，不存在的插入到数据库中
 */
public class RocketMQClusterJobService extends InstanceService<IClusterService> {

    private RocketmqClusterInfoMapper clusterInfoMapper;
    private RocketmqBrokerInfoMapper brokerInfoMapper;
    private RocketmqClusterConfigMapper clusterConfigMapper;

    @Transactional
    public void pullCluster() {
        // 获取插件列表
        Set<String> contextNames = pluginNamedContextFactory.getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            return;
        }

        // 遍历插件列表
        contextNames.forEach(instanceName -> {
            IClusterService clusterService = getApi(instanceName);

            clusterInfoMapper.truncateTable();
            brokerInfoMapper.truncateTable();
            clusterConfigMapper.truncateTable();

            BrokerServerDTO result = clusterService.list();

            if (result == null) {
                return;
            }
            // broker的详情
            Map<String, Map<String, BrokerDetailDTO>> brokerServer = result.getBrokerServer();

            // broker与集群关系
            Map<String, BrokerInfoDTO> brokerAddrTable = result.getClusterInfo().getBrokerAddrTable();

            // User user = UserContext.getUser();

            // 获取broker与集群关系的信息，然后根据集群id查找broker详情
            brokerAddrTable.forEach((brokerName, brokerInfoDTO) -> {
                RocketmqClusterInfo rocketmqClusterInfo = new RocketmqClusterInfo();
                rocketmqClusterInfo.setClusterName(brokerInfoDTO.getCluster());

                RocketmqClusterInfo dbRocketmqClusterInfo =
                    clusterInfoMapper.selectByClusterName(brokerInfoDTO.getCluster());
                clusterInfoMapper.insertSelective(rocketmqClusterInfo);

                Map<String, BrokerDetailDTO> brokerMap = brokerServer.get(brokerName);
                // 这里只有一个数据
                brokerMap.forEach((brokerId, brokerDetailDTO) -> {
                    ClusterServiceConvert mapper = Mappers.getMapper(ClusterServiceConvert.class);
                    RocketmqBrokerInfo rocketmqBrokerInfo = mapper.convert(brokerDetailDTO);

                    // 设置clusterid
                    if (dbRocketmqClusterInfo == null) {
                        rocketmqBrokerInfo.setClusterId(rocketmqClusterInfo.getId());
                    } else {
                        rocketmqBrokerInfo.setClusterId(dbRocketmqClusterInfo.getId());
                    }

                    String brokerAddr = brokerInfoDTO.getBrokerAddrs().get(brokerId);

                    rocketmqBrokerInfo.setClientAddr(brokerAddr);
                    rocketmqBrokerInfo.setBrokerName(brokerName);

                    brokerInfoMapper.insertSelective(rocketmqBrokerInfo);

                    // 查询集群配置
                    ClusterConfigInfoDTO clusterConfigInfoDTO = clusterService.queryConfig(brokerAddr);

                    ClusterConfigServiceConvert clusterConfigServiceConvert =
                        Mappers.getMapper(ClusterConfigServiceConvert.class);
                    RocketmqClusterConfig rocketmqClusterConfig =
                        clusterConfigServiceConvert.convert(clusterConfigInfoDTO);
                    clusterConfigMapper.insertSelective(rocketmqClusterConfig);
                });

            });

        });

    }

    public RocketMQClusterJobService(PluginNamedContextFactory pluginNamedContextFactory,
        RocketmqClusterInfoMapper clusterInfoMapper, RocketmqBrokerInfoMapper brokerInfoMapper,
        RocketmqClusterConfigMapper clusterConfigMapper) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
        this.clusterInfoMapper = clusterInfoMapper;
        this.brokerInfoMapper = brokerInfoMapper;
        this.clusterConfigMapper = clusterConfigMapper;
    }
}
