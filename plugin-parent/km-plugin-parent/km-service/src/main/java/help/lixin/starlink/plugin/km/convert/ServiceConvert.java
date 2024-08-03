package help.lixin.starlink.plugin.km.convert;

import java.util.List;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.km.api.dto.cluster.*;
import help.lixin.starlink.plugin.km.api.response.*;

@Mapper
public interface ServiceConvert {

    KafkaBSValidateDTO convert(KafkaBSValidate kafkaBSValidate);

    ClusterPhyAdd convert(ClusterPhyAddDTO clusterPhyAddDTO);

    ClusterPhysStateResponse convert(ClusterPhysState clusterPhysState);

    MultiClusterDashboard convert(MultiClusterDashboardDTO multiClusterDashboardDTO);

    List<ClusterPhyDashboardResponse> convertClusterPhyDashboard(List<ClusterPhyDashboard> clusterPhyDashboard);

    List<TopicOverviewDTO> convertTopicOverview(List<TopicOverview> topicOverview);

    ClusterPhyUpdate convert(ClusterPhyUpdateDTO clusterPhyUpdateDTO);

    ClusterPhyBaseResponse convert(ClusterPhyBase clusterPhyBase);

}
