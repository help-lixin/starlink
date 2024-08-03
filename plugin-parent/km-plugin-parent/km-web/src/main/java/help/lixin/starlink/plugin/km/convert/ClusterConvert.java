package help.lixin.starlink.plugin.km.convert;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import help.lixin.starlink.plugin.km.api.dto.cluster.ClusterPhyAddDTO;
import help.lixin.starlink.plugin.km.api.dto.cluster.ClusterPhyUpdateDTO;
import help.lixin.starlink.plugin.km.api.dto.cluster.MultiClusterDashboardDTO;
import help.lixin.starlink.plugin.km.request.ClusterPhyAddRequest;
import help.lixin.starlink.plugin.km.request.ClusterPhyUpdateRequest;
import help.lixin.starlink.plugin.km.request.MultiClusterDashboardRequest;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/15 10:47 上午
 * @Description
 */
@Mapper
public interface ClusterConvert {

    ClusterPhyAddDTO convert(ClusterPhyAddRequest clusterPhyAddRequest);

    @Mapping(source = "pageNum", target = "pageNo")
    MultiClusterDashboardDTO convert(MultiClusterDashboardRequest multiClusterDashboardRequest);

    ClusterPhyUpdateDTO convert(ClusterPhyUpdateRequest clusterPhyUpdateRequest);
}
