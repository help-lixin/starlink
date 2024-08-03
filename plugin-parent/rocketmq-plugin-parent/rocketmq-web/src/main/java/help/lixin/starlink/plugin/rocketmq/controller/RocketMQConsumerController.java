package help.lixin.starlink.plugin.rocketmq.controller;

import static help.lixin.response.Response.success;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.response.Response;
import help.lixin.starlink.plugin.rocketmq.api.dto.consumer.*;
import help.lixin.starlink.plugin.rocketmq.convert.ConsumerConvert;
import help.lixin.starlink.plugin.rocketmq.request.consumer.ConsumerDeleteRequest;
import help.lixin.starlink.plugin.rocketmq.request.consumer.ConsumerSubscriptionGroupInfoRequest;
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQConsumerService;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 4:37 下午
 * @Description
 */
@Api(tags = "消费者控制层")
@RestController
@RequestMapping("/rocketmq/consumer")
public class RocketMQConsumerController {

    @Autowired
    private IRocketMQConsumerService rocketMQConsumerService;

    @GetMapping("/list")
    @ApiOperation(value = "消费者列表")
    public Response<List<ConsumerListDTO>> groupList(@Valid EnvRequest envRequest) {
        return success(rocketMQConsumerService.groupList(envRequest.toInstanceName()));
    }

    @GetMapping("/{consumerGroup}/connection/info")
    @ApiOperation(value = "消费者终端信息")
    public Response<ConsumerConnectionInfoDTO> consumerConnection(@PathVariable String consumerGroup,
        @Valid EnvRequest envRequest) {
        return success(rocketMQConsumerService.consumerConnection(consumerGroup, envRequest.toInstanceName()));
    }

    @GetMapping("/{consumerGroup}/consumerConnection")
    @ApiOperation(value = "消费者详情")
    public Response<List<ConsumerOffsetInfoDTO>> queryTopicByConsumer(@PathVariable String consumerGroup,
        @Valid EnvRequest envRequest) {
        return success(rocketMQConsumerService.queryTopicByConsumer(consumerGroup, envRequest.toInstanceName()));
    }

    @GetMapping("/{consumerGroup}/examineSubscriptionGroupConfig")
    @ApiOperation(value = "消费者配置信息")
    public Response<List<ConsumerSubscriptionGroupDataDTO>>
        examineSubscriptionGroupConfig(@PathVariable String consumerGroup, @Valid EnvRequest envRequest) {
        return success(
            rocketMQConsumerService.examineSubscriptionGroupConfig(consumerGroup, envRequest.toInstanceName()));
    }

    @PostMapping("/createOrUpdate")
    @ApiOperation(value = "创建、更新消费者信息")
    public Response<Boolean> createOrUpdate(
        @RequestBody ConsumerSubscriptionGroupInfoRequest consumerSubscriptionGroupInfoRequest,
        @Valid EnvRequest envRequest) {
        ConsumerConvert consumerConvert = Mappers.getMapper(ConsumerConvert.class);
        ConsumerSubscriptionGroupInfoDTO consumerSubscriptionGroupInfoDTO =
            consumerConvert.convert(consumerSubscriptionGroupInfoRequest);
        return success(
            rocketMQConsumerService.createOrUpdate(consumerSubscriptionGroupInfoDTO, envRequest.toInstanceName()));
    }

    @GetMapping("/{consumerGroup}/createOrUpdate")
    @ApiOperation(value = "查询broker列表")
    public Response<List<String>> fetchBrokerNameList(@PathVariable String consumerGroup,
        @Valid EnvRequest envRequest) {
        return success(rocketMQConsumerService.fetchBrokerNameList(consumerGroup, envRequest.toInstanceName()));
    }

    @DeleteMapping("/del")
    @ApiOperation(value = "删除消费者")
    public Response<Boolean> deleteSubGroup(@RequestBody @Valid ConsumerDeleteRequest consumerDeleteRequest,
        @Valid EnvRequest envRequest) {
        ConsumerConvert consumerConvert = Mappers.getMapper(ConsumerConvert.class);
        ConsumerDeleteDTO consumerDeleteDTO = consumerConvert.convert(consumerDeleteRequest);
        return success(rocketMQConsumerService.deleteSubGroup(consumerDeleteDTO, envRequest.toInstanceName()));
    }

    @GetMapping("/{topicName}/resetOffset")
    @ApiOperation(value = "重置消费点位")
    public Response<List<String>> resetOffset(@PathVariable String topicName, @Valid EnvRequest envRequest) {
        return success(rocketMQConsumerService.resetOffset(topicName, envRequest.toInstanceName()));
    }
}
