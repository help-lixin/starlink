package help.lixin.starlink.plugin.km.controller;

import static help.lixin.response.Response.success;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.km.api.dto.cluster.*;
import help.lixin.starlink.plugin.km.convert.ClusterConvert;
import help.lixin.starlink.plugin.km.request.ClusterPhyAddRequest;
import help.lixin.starlink.plugin.km.request.ClusterPhyUpdateRequest;
import help.lixin.starlink.plugin.km.request.MultiClusterDashboardRequest;
import help.lixin.starlink.plugin.km.service.IKafkaConnectorStateService;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/14 12:38 下午
 * @Description
 */
@Api(tags = "kafka连接器状态查询")
@RestController
@RequestMapping("/km/kafka/connector")
public class KafkaConnectorStateController {

    @Autowired
    private IKafkaConnectorStateService kafkaConnectorStateService;

    @GetMapping("/cluster")
    @ApiOperation("查询集群名称是否存在")
    public Response<Boolean> clusterNameExist(@RequestParam String name, @Valid EnvRequest envRequest) {
        return success(kafkaConnectorStateService.getClusterPhyBasicCombineExist(name, envRequest.toInstanceName()));
    }

    @GetMapping("/validate/kafka")
    @ApiOperation("验证kafka地址是否存在并带出相关信息")
    public Response<KafkaBSValidateDTO> validateKafka(@RequestParam String bootstrapServices,
        @Valid EnvRequest envRequest) {
        return success(kafkaConnectorStateService.validateKafka(bootstrapServices, envRequest.toInstanceName()));
    }

    @PostMapping("/add")
    @ApiOperation("添加集群")
    public Response<KafkaBSValidateDTO> addPhysicalClusters(
        @RequestBody @Valid ClusterPhyAddRequest clusterPhyAddRequest, @Valid EnvRequest envRequest) {
        ClusterConvert convert = Mappers.getMapper(ClusterConvert.class);
        ClusterPhyAddDTO clusterPhyAddDTO = convert.convert(clusterPhyAddRequest);
        return success(kafkaConnectorStateService.addPhysicalClusters(clusterPhyAddDTO, envRequest.toInstanceName()));
    }

    @GetMapping("/state")
    @ApiOperation("查询状态列表")
    public Response<KafkaBSValidateDTO> state(@Valid EnvRequest envRequest) {
        return success(kafkaConnectorStateService.state(envRequest.toInstanceName()));
    }

    @PostMapping("/clusterList")
    @ApiOperation("查询集群列表")
    public Response<PageResponse<KafkaBSValidateDTO>> clusterList(
        @RequestBody @Valid MultiClusterDashboardRequest multiClusterDashboardRequest, @Valid EnvRequest envRequest) {
        ClusterConvert convert = Mappers.getMapper(ClusterConvert.class);
        MultiClusterDashboardDTO multiClusterDashboardDTO = convert.convert(multiClusterDashboardRequest);
        return success(kafkaConnectorStateService.list(multiClusterDashboardDTO, envRequest.toInstanceName()));
    }

    @PostMapping("/{clusterId}/topicList")
    @ApiOperation("根据集群id查询集群topic列表")
    public Response<PageResponse<TopicOverviewDTO>> topicList(
        @RequestBody @Valid MultiClusterDashboardRequest multiClusterDashboardRequest, @Valid EnvRequest envRequest,
        @PathVariable Integer clusterId) {
        ClusterConvert convert = Mappers.getMapper(ClusterConvert.class);
        MultiClusterDashboardDTO multiClusterDashboardDTO = convert.convert(multiClusterDashboardRequest);
        return success(
            kafkaConnectorStateService.topicList(multiClusterDashboardDTO, clusterId, envRequest.toInstanceName()));
    }

    @DeleteMapping("/{clusterId}/del")
    @ApiOperation("删除")
    public Response<Boolean> del(@PathVariable Integer clusterId, @Valid EnvRequest envRequest) {
        return success(kafkaConnectorStateService.del(clusterId, envRequest.toInstanceName()));
    }

    @PutMapping("/edit")
    @ApiOperation("更新集群配置")
    public Response<Boolean> update(@RequestBody @Valid ClusterPhyUpdateRequest clusterPhyUpdateRequest,
        @Valid EnvRequest envRequest) {
        ClusterConvert mapper = Mappers.getMapper(ClusterConvert.class);
        ClusterPhyUpdateDTO clusterPhyUpdateDTO = mapper.convert(clusterPhyUpdateRequest);
        return success(kafkaConnectorStateService.update(clusterPhyUpdateDTO, envRequest.toInstanceName()));
    }

    @GetMapping("/{clusterId}/info")
    @ApiOperation("查询集群详情")
    public Response<Boolean> info(@PathVariable Integer clusterId, @Valid EnvRequest envRequest) {
        return success(kafkaConnectorStateService.detail(clusterId, envRequest.toInstanceName()));
    }
}
