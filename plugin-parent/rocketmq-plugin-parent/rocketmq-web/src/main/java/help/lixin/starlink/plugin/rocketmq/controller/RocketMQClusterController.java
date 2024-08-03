package help.lixin.starlink.plugin.rocketmq.controller;

import static help.lixin.response.Response.success;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.core.convert.PageConvert;
import help.lixin.starlink.core.dto.PageDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.broker.BrokerServerDTO;
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQClusterService;
import help.lixin.starlink.request.EnvRequest;
import help.lixin.starlink.request.PageRequest;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/24 6:01 下午
 * @Description
 */
@RestController
@RequestMapping("/rocketmq/cluster")
public class RocketMQClusterController {

    @Autowired
    private IRocketMQClusterService rocketMQClusterService;

    @GetMapping("/{clusterName}/list")
    @ApiOperation(value = "查询集群列表")
    public Response<PageResponse<BrokerServerDTO>> list(@PathVariable String clusterName, //
        PageRequest pageRequest, //
        @Valid EnvRequest envRequest) {
        PageConvert mapper = Mappers.getMapper(PageConvert.class);
        PageDTO pageDTO = mapper.pageConvert(pageRequest);
        return success(rocketMQClusterService.pageList(clusterName, pageDTO, envRequest.toInstanceName()));
    }

    @GetMapping("/{brokerAddr}/info")
    @ApiOperation(value = "查询集群配置信息")
    public Response configInfo(@PathVariable String brokerAddr, //
        @Valid EnvRequest envRequest) {
        return success(rocketMQClusterService.queryConfig(brokerAddr, envRequest.toInstanceName()));
    }

    @GetMapping("/clusterNameList")
    @ApiOperation(value = "查询集群配置信息")
    public Response clusterNameList() {
        return success(rocketMQClusterService.clusterNameList());
    }
}
