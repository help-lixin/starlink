package help.lixin.starlink.plugin.km.service.impl;

import java.util.List;

import org.mapstruct.factory.Mappers;

import help.lixin.response.PageResponse;
import help.lixin.starlink.core.plugin.InstanceService;
import help.lixin.starlink.core.plugin.PluginNamedContextFactory;
import help.lixin.starlink.plugin.km.api.dto.cluster.*;
import help.lixin.starlink.plugin.km.api.response.ClusterPhyBaseResponse;
import help.lixin.starlink.plugin.km.api.response.ClusterPhyDashboard;
import help.lixin.starlink.plugin.km.api.response.ClusterPhyDashboardResponse;
import help.lixin.starlink.plugin.km.api.response.ClusterPhysStateResponse;
import help.lixin.starlink.plugin.km.api.service.IKmKafkaConnectorStateService;
import help.lixin.starlink.plugin.km.convert.ServiceConvert;
import help.lixin.starlink.plugin.km.service.IKafkaConnectorStateService;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/14 12:34 下午
 * @Description
 */
public class KafkaConnectorStateServiceImpl extends InstanceService<IKmKafkaConnectorStateService>
    implements IKafkaConnectorStateService {

    @Override
    public Boolean getClusterPhyBasicCombineExist(String clusterPhyName, String instanceName) {
        return getApi(instanceName).getClusterPhyBasicCombineExist(clusterPhyName);
    }

    @Override
    public KafkaBSValidateDTO validateKafka(String bootstrapServices, String instanceName) {
        KafkaBSValidate kafkaBSValidate = getApi(instanceName).validateKafka(bootstrapServices);
        ServiceConvert mapper = Mappers.getMapper(ServiceConvert.class);
        return mapper.convert(kafkaBSValidate);
    }

    @Override
    public Boolean addPhysicalClusters(ClusterPhyAddDTO clusterPhyAddDTO, String instanceName) {
        ServiceConvert mapper = Mappers.getMapper(ServiceConvert.class);
        return getApi(instanceName).addPhysicalClusters(mapper.convert(clusterPhyAddDTO));
    }

    @Override
    public ClusterPhysStateResponse state(String instanceName) {
        ServiceConvert mapper = Mappers.getMapper(ServiceConvert.class);
        return mapper.convert(getApi(instanceName).state());
    }

    @Override
    public PageResponse<ClusterPhyDashboardResponse> list(MultiClusterDashboardDTO multiClusterDashboardDTO,
        String instanceName) {
        ServiceConvert mapper = Mappers.getMapper(ServiceConvert.class);
        PageResponse<ClusterPhyDashboard> pageResponse =
            getApi(instanceName).list(mapper.convert(multiClusterDashboardDTO));
        List<ClusterPhyDashboard> records = pageResponse.getRecords();

        PageResponse<ClusterPhyDashboardResponse> response = new PageResponse<>();
        response.setTotal(pageResponse.getTotal());
        response.setPageCurrent(pageResponse.getPageCurrent());
        response.setPageSize(pageResponse.getPageSize());
        response.setRecords(mapper.convertClusterPhyDashboard(records));

        return response;
    }

    @Override
    public PageResponse<TopicOverviewDTO> topicList(MultiClusterDashboardDTO multiClusterDashboardDTO,
        Integer clusterId, String instanceName) {
        ServiceConvert mapper = Mappers.getMapper(ServiceConvert.class);
        PageResponse<TopicOverview> pageResponse =
            getApi(instanceName).topicList(mapper.convert(multiClusterDashboardDTO), clusterId);
        List<TopicOverview> records = pageResponse.getRecords();

        PageResponse<TopicOverviewDTO> response = new PageResponse<>();
        response.setTotal(pageResponse.getTotal());
        response.setPageCurrent(pageResponse.getPageCurrent());
        response.setPageSize(pageResponse.getPageSize());
        response.setRecords(mapper.convertTopicOverview(records));

        return response;
    }

    @Override
    public Boolean del(Integer clusterId, String instanceName) {
        return getApi(instanceName).del(clusterId);
    }

    @Override
    public Boolean update(ClusterPhyUpdateDTO clusterPhyUpdateDTO, String instanceName) {
        ServiceConvert mapper = Mappers.getMapper(ServiceConvert.class);
        return getApi(instanceName).update(mapper.convert(clusterPhyUpdateDTO));
    }

    @Override
    public ClusterPhyBaseResponse detail(Integer clusterId, String instanceName) {
        ServiceConvert mapper = Mappers.getMapper(ServiceConvert.class);
        return mapper.convert(getApi(instanceName).detail(clusterId));
    }

    public KafkaConnectorStateServiceImpl(PluginNamedContextFactory pluginNamedContextFactory) {
        this.pluginNamedContextFactory = pluginNamedContextFactory;
    }

}
