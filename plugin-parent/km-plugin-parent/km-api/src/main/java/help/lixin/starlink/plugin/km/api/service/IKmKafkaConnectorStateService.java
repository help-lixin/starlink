package help.lixin.starlink.plugin.km.api.service;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.km.api.dto.cluster.*;
import help.lixin.starlink.plugin.km.api.response.ClusterPhyBase;
import help.lixin.starlink.plugin.km.api.response.ClusterPhyDashboard;
import help.lixin.starlink.plugin.km.api.response.ClusterPhysState;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/14 11:50 上午
 * @Description
 */
public interface IKmKafkaConnectorStateService {

    /**
     * @Param clusterPhyName :
     * @Author: 伍岳林
     * @Date: 2023/8/14 12:02 下午
     * @Return: java.lang.Boolean
     * @Description 查看当前集群名称是否被使用 true:被使用
     */
    Boolean getClusterPhyBasicCombineExist(String clusterPhyName);

    /**
     * @Param bootstrapServices :
     * @Author: 伍岳林
     * @Date: 2023/8/14 10:54 下午
     * @Return: help.lixin.starlink.plugin.km.api.dto.connector.KafkaBSValidateDTO
     * @Description 验证kafka地址是否存在并带出相关信息
     */
    KafkaBSValidate validateKafka(String bootstrapServices);

    /**
     * @Param addPhysicalClusters :
     * @Author: 伍岳林
     * @Date: 2023/8/15 10:38 上午
     * @Return: java.lang.Boolean
     * @Description 添加集群
     */
    Boolean addPhysicalClusters(ClusterPhyAdd clusterPhyAddDTO);

    /**
     * @Author: 伍岳林
     * @Date: 2023/8/15 11:26 上午
     * @Return: help.lixin.starlink.plugin.km.api.response.ClusterPhysState
     * @Description 获取所有集群当前状态
     */
    ClusterPhysState state();

    /**
     * @Param multiClusterDashboardDTO :
     * @Author: 伍岳林
     * @Date: 2023/8/15 3:10 下午
     * @Return: help.lixin.starlink.plugin.km.api.response.ClusterPhyDashboardResponse
     * @Description 集群列表
     * @return
     */
    PageResponse<ClusterPhyDashboard> list(MultiClusterDashboard multiClusterDashboardDTO);

    /**
     * @Param multiClusterDashboardDTO :
     * @Param id : 集群id
     * @Author: 伍岳林
     * @Date: 2023/8/15 6:21 下午
     * @Return: java.util.List<help.lixin.starlink.plugin.km.api.response.ClusterPhyDashboardResponse>
     * @Description
     * @return
     */
    PageResponse<TopicOverview> topicList(MultiClusterDashboard multiClusterDashboardDTO, Integer id);

    /**
     * @Param id :
     * @Author: 伍岳林
     * @Date: 2023/8/15 7:56 下午
     * @Return: help.lixin.starlink.plugin.km.api.response.ClusterPhyBaseResponse
     * @Description 查询集群详情
     */
    ClusterPhyBase detail(Integer id);

    Boolean del(Integer id);

    Boolean update(ClusterPhyUpdate clusterPhyUpdateDTO);
}
