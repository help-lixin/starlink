package help.lixin.starlink.plugin.rocketmq.controller;

import static help.lixin.response.Response.success;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.response.Response;
import help.lixin.starlink.plugin.rocketmq.api.dto.consumer.ConsumerConnectionInfoDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.consumer.ConsumerListDTO;
import help.lixin.starlink.plugin.rocketmq.api.dto.consumer.ConsumerOffsetInfoDTO;
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQNameSvrAddrService;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 4:37 下午
 * @Description
 */
@Api(tags = "OPS控制层")
@RestController
@RequestMapping("/rocketmq/namesvraddr")
public class RocketMQNameSvrAddrController {
    @Autowired
    private IRocketMQNameSvrAddrService rocketMQNameSvrAddrService;

    @GetMapping("/list")
    @ApiOperation(value = "地址列表")
    public Response<List<ConsumerListDTO>> list(@Valid EnvRequest envRequest) {
        return success(rocketMQNameSvrAddrService.list(envRequest.toInstanceName()));
    }

    @PostMapping("/updateIsVIPChannel")
    @ApiOperation(value = "更新VIP通道方法")
    public Response<ConsumerConnectionInfoDTO> updateIsVIPChannel(@RequestBody Boolean useVIPChannel,
        @Valid EnvRequest envRequest) {
        return success(rocketMQNameSvrAddrService.updateIsVIPChannel(useVIPChannel, envRequest.toInstanceName()));
    }

    @PostMapping("/updateNameSvrAddr")
    @ApiOperation(value = "根据当前地址列表更新namesvr")
    public Response<List<ConsumerOffsetInfoDTO>> updateNameSvrAddr(String nameSvrAddrList,
        @Valid EnvRequest envRequest) {
        return success(rocketMQNameSvrAddrService.updateNameSvrAddr(nameSvrAddrList, envRequest.toInstanceName()));
    }
}
