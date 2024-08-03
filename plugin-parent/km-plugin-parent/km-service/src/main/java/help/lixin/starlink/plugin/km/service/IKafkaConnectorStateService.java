package help.lixin.starlink.plugin.km.service;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.km.api.dto.cluster.*;
import help.lixin.starlink.plugin.km.api.response.ClusterPhyBaseResponse;
import help.lixin.starlink.plugin.km.api.response.ClusterPhyDashboardResponse;
import help.lixin.starlink.plugin.km.api.response.ClusterPhysStateResponse;

public interface IKafkaConnectorStateService {

    /**
     * @Param clusterPhyName : 集群名称
     * @Param instanceName : 实例名
     * @Author: 伍岳林
     * @Date: 2023/8/14 12:02 下午
     * @Return: java.lang.Boolean
     * @Description 查看当前集群名称是否被使用 true:被使用
     */
    Boolean getClusterPhyBasicCombineExist(String clusterPhyName, String instanceName);

    /**
     * @Param bootstrapServices :
     * @Author: 伍岳林
     * @Date: 2023/8/14 10:54 下午
     * @Return: help.lixin.starlink.plugin.km.api.dto.connector.KafkaBSValidateDTO
     * @Description 验证kafka地址是否存在并带出相关信息
     * @return
     */
    KafkaBSValidateDTO validateKafka(String bootstrapServices, String instanceName);

    /**
     * @Param clusterPhyAddDTO :
     * @Author: 伍岳林
     * @Date: 2023/8/15 10:38 上午
     * @Return: java.lang.Boolean
     * @Description 添加集群
     */
    Boolean addPhysicalClusters(ClusterPhyAddDTO clusterPhyAddDTO, String instanceName);

    /**
     * @Author: 伍岳林
     * @Date: 2023/8/15 11:26 上午
     * @Return: help.lixin.starlink.plugin.km.api.response.ClusterPhysState
     * @Description 获取所有集群当前状态
     * @return
     */
    ClusterPhysStateResponse state(String instanceName);

    /**
     * @Param multiClusterDashboardDTO :
     * @Author: 伍岳林
     * @Date: 2023/8/15 3:10 下午
     * @Return: help.lixin.starlink.plugin.km.api.response.ClusterPhyDashboardResponse
     * @Description 集群列表
     * @return
     */
    PageResponse<ClusterPhyDashboardResponse> list(MultiClusterDashboardDTO multiClusterDashboardDTO,
        String instanceName);

    /**
     * @Param multiClusterDashboardDTO :
     * @Param clusterId : 集群id
     * @Author: 伍岳林
     * @Date: 2023/8/15 6:21 下午
     * @Return: java.util.List<help.lixin.starlink.plugin.km.api.response.ClusterPhyDashboardResponse>
     * @Description
     * @return
     */
    PageResponse<TopicOverviewDTO> topicList(MultiClusterDashboardDTO multiClusterDashboardDTO, Integer clusterId,
        String instanceName);

    Boolean del(Integer clusterId, String instanceName);

    Boolean update(ClusterPhyUpdateDTO clusterPhyUpdateDTO, String instanceName);

    ClusterPhyBaseResponse detail(Integer clusterId, String instanceName);
}
