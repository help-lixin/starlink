package help.lixin.starlink.plugin.rocketmq.service.impl;

import help.lixin.authorize.user.context.User;
import help.lixin.authorize.user.context.UserContext;
import help.lixin.starlink.core.dto.PageDTO;
import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.response.PageResponse;
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
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQClusterService;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/24 5:59 下午
 * @Description
 */
public class RocketMQClusterServiceImpl extends InstanceService<IClusterService> implements IRocketMQClusterService {

    private RocketmqBrokerInfoMapper brokerInfoMapper;
    private RocketmqClusterConfigMapper clusterConfigMapper;
    private RocketmqClusterInfoMapper clusterInfoMapper;
    private QueryTemplate queryTemplate;


    @Override
    @Transactional
    public PageResponse<BrokerServerDTO> pageList(String clusterName, PageDTO pageDTO, String instanceName) {
        BrokerServerDTO result = getApi(instanceName).list();

        AtomicReference<Long> clusterId = new AtomicReference(0);

        if (result == null) {
            RocketmqClusterInfo dbRocketmqClusterInfo = clusterInfoMapper.selectByClusterName(clusterName);
            return queryTemplate.execute(pageDTO, () -> {
                brokerInfoMapper.selectByClusterId(dbRocketmqClusterInfo.getId());
            });
        } else {
            //broker的详情
            Map<String, Map<String, BrokerDetailDTO>> brokerServer = result.getBrokerServer();

            //broker与集群关系
            Map<String, BrokerInfoDTO> brokerAddrTable = result.getClusterInfo().getBrokerAddrTable();

            User user = UserContext.getUser();

            //获取broker与集群关系的信息，然后根据集群id查找broker详情
            brokerAddrTable.forEach((brokerName, brokerInfoDTO) -> {
                RocketmqClusterInfo rocketmqClusterInfo = new RocketmqClusterInfo();
                rocketmqClusterInfo.setClusterName(brokerInfoDTO.getCluster());

                RocketmqClusterInfo dbRocketmqClusterInfo = clusterInfoMapper.selectByClusterName(brokerInfoDTO.getCluster());
                if (dbRocketmqClusterInfo == null) {
                    rocketmqClusterInfo.setCreateBy(user.getUserId().toString());
                    clusterInfoMapper.insertSelective(rocketmqClusterInfo);

                    if (clusterName.equals(brokerInfoDTO.getCluster())) {
                        clusterId.set(rocketmqClusterInfo.getId());
                    }

                } else {
                    rocketmqClusterInfo.setStatus(1);
                    rocketmqClusterInfo.setUpdateBy(user.getUserId().toString());
                    clusterInfoMapper.updateByPrimaryKeySelective(rocketmqClusterInfo);


                    if (clusterName.equals(brokerInfoDTO.getCluster())) {
                        clusterId.set(dbRocketmqClusterInfo.getId());
                    }
                }


                Map<String, BrokerDetailDTO> brokerMap = brokerServer.get(brokerName);
                //这里只有一个数据
                brokerMap.forEach((brokerId, brokerDetailDTO) -> {
                    ClusterServiceConvert mapper = Mappers.getMapper(ClusterServiceConvert.class);
                    RocketmqBrokerInfo rocketmqBrokerInfo = mapper.convert(brokerDetailDTO);
                    //根据broker名称查询库中是否存在
                    RocketmqBrokerInfo dbRocketMQBrokerInfo = brokerInfoMapper.selectByBrokerName(brokerName);

                    //设置clusterid
                    if (dbRocketmqClusterInfo == null) {
                        rocketmqBrokerInfo.setClusterId(rocketmqClusterInfo.getId());
                    } else {
                        rocketmqBrokerInfo.setClusterId(dbRocketmqClusterInfo.getId());
                    }

                    rocketmqBrokerInfo.setClientAddr(brokerInfoDTO.getBrokerAddrs().get(brokerId));
                    rocketmqBrokerInfo.setBrokerName(brokerName);

                    //库中不存在broker则插入
                    if (dbRocketMQBrokerInfo == null) {
                        brokerInfoMapper.insertSelective(rocketmqBrokerInfo);
                    } else {  //存在则更新，id随着rocketmq的id发生变化
                        rocketmqBrokerInfo.setId(dbRocketMQBrokerInfo.getId());
                        rocketmqBrokerInfo.setStatus(1);
                        rocketmqBrokerInfo.setUpdateBy(user.getUserId().toString());
                        rocketmqBrokerInfo.setUpdateTime(new Date());
                        brokerInfoMapper.updateByPrimaryKeySelective(rocketmqBrokerInfo);
                    }

                });


            });


        }

        return queryTemplate.execute(pageDTO, () -> {
            brokerInfoMapper.selectByClusterId(clusterId.get());
        });
    }

    @Override
    public List<RocketmqClusterInfo> clusterNameList() {
        return clusterInfoMapper.selectClusterList();
    }

    @Override
    @Transactional
    public RocketmqClusterConfig queryConfig(String brokerAddr, String instanceName) {
        ClusterConfigInfoDTO clusterConfigInfoDTO = getApi(instanceName).queryConfig(brokerAddr);

        //返回为空的情况下直接去数据库取数据
        if (clusterConfigInfoDTO == null) {
            String[] addrs = brokerAddr.split(":");
            return clusterConfigMapper.selectByAddr(addrs[0], addrs[1]);
        }

        ClusterConfigServiceConvert mapper = Mappers.getMapper(ClusterConfigServiceConvert.class);
        RocketmqClusterConfig clusterConfig = mapper.convert(clusterConfigInfoDTO);

        String brokerName = clusterConfig.getBrokerName();
        String clusterName = clusterConfig.getClusterName();

        RocketmqClusterConfig rocketmqClusterConfig = clusterConfigMapper.selectByBrokerNameAndClusterName(brokerName, clusterName);

        User user = UserContext.getUser();

        if (rocketmqClusterConfig == null) {
            clusterConfig.setCreateBy(user.getUserId().toString());
            clusterConfigMapper.insertSelective(clusterConfig);
            return clusterConfig;
        } else {
            rocketmqClusterConfig.setStatus(1);
            rocketmqClusterConfig.setUpdatedTime(new Date());
            rocketmqClusterConfig.setUpdateBy(user.getUserId().toString());
            clusterConfigMapper.updateByPrimaryKeySelective(rocketmqClusterConfig);

            return rocketmqClusterConfig;
        }

    }

    public RocketMQClusterServiceImpl(PluginNamedContextFactory pluginNamedContextFactory,
                                      RocketmqBrokerInfoMapper brokerInfoMapper,
                                      RocketmqClusterConfigMapper clusterConfigMapper,
                                      RocketmqClusterInfoMapper clusterInfoMapper,
                                      QueryTemplate queryTemplate) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
        this.brokerInfoMapper = brokerInfoMapper;
        this.clusterConfigMapper = clusterConfigMapper;
        this.clusterInfoMapper = clusterInfoMapper;
        this.queryTemplate = queryTemplate;
    }
}
