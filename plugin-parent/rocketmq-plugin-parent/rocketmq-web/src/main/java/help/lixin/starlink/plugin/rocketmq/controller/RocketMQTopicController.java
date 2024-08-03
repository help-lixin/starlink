package help.lixin.starlink.plugin.rocketmq.controller;

import static help.lixin.response.Response.success;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.response.Response;
import help.lixin.starlink.plugin.rocketmq.api.dto.topic.*;
import help.lixin.starlink.plugin.rocketmq.convert.TopicConvert;
import help.lixin.starlink.plugin.rocketmq.request.topic.TopicInfoRequest;
import help.lixin.starlink.plugin.rocketmq.request.topic.TopicSendMessageRequest;
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQTopicService;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/23 10:55 上午
 * @Description
 */
@RestController
@RequestMapping("/rocketmq/topic")
@Api(tags = "RocketMQ_Topic相关接口")
public class RocketMQTopicController {

    @Autowired
    private IRocketMQTopicService rocketMQTopicService;

    @GetMapping("/list")
    @ApiOperation(value = "查询topic列表")
    public Response<List<String>> queryList(@Valid EnvRequest envRequest) {
        return success(rocketMQTopicService.queryList(envRequest.toInstanceName()));
    }

    @GetMapping("/{topicName}/status")
    @ApiOperation(value = "topic详细状态信息")
    public Response<Map<String, TopicStatusMessageQueueDTO>> queryStatus(@PathVariable String topicName,
        @Valid EnvRequest envRequest) {
        return success(rocketMQTopicService.queryStatus(topicName, envRequest.toInstanceName()));
    }

    @GetMapping("/{topicName}/route")
    @ApiOperation(value = "topic路由信息")
    public Response<TopicRouteDTO> queryRoute(@PathVariable String topicName, @Valid EnvRequest envRequest) {
        return success(rocketMQTopicService.queryRoute(topicName, envRequest.toInstanceName()));
    }

    @GetMapping("/{topicName}/consumer")
    @ApiOperation(value = "consumer管理页面")
    public Response<Map<String, TopicConsumerDTO>> queryConsumerByTopic(@PathVariable String topicName,
        @Valid EnvRequest envRequest) {
        return success(rocketMQTopicService.queryConsumerByTopic(topicName, envRequest.toInstanceName()));
    }

    @GetMapping("/{topicName}/consumer/list")
    @ApiOperation(value = "consumer订阅组列表")
    public Response<List<String>> queryTopicConsumerList(@PathVariable String topicName, @Valid EnvRequest envRequest) {
        return success(rocketMQTopicService.queryTopicConsumerList(topicName, envRequest.toInstanceName()));
    }

    @PutMapping("/update")
    @ApiOperation(value = "更新topic信息")
    public Response<Boolean> update(@Valid @RequestBody TopicInfoRequest topicInfoRequest,
        @Valid EnvRequest envRequest) {
        TopicConvert mapper = Mappers.getMapper(TopicConvert.class);
        TopicInfoDTO topicInfoDTO = mapper.convert(topicInfoRequest);
        return success(rocketMQTopicService.saveOrUpdate(topicInfoDTO, envRequest.toInstanceName()));
    }

    @GetMapping("/{topicName}/info")
    @ApiOperation(value = "查询topic详情信息")
    public Response<List<TopicInfoDTO>> queryTopicInfo(@PathVariable String topicName, @Valid EnvRequest envRequest) {
        return success(rocketMQTopicService.queryTopicInfo(topicName, envRequest.toInstanceName()));
    }

    @PostMapping("/send/message")
    @ApiOperation(value = "发送消息")
    public Response<TopicMessageStatusDTO>
        sendMessage(@Valid @RequestBody TopicSendMessageRequest topicSendMessageRequest, @Valid EnvRequest envRequest) {
        TopicConvert mapper = Mappers.getMapper(TopicConvert.class);
        TopicSendMessageDTO topicSendMessageDTO = mapper.convert(topicSendMessageRequest);
        return success(rocketMQTopicService.sendMessage(topicSendMessageDTO, envRequest.toInstanceName()));
    }

    @DeleteMapping("{topicName}/del")
    @ApiOperation(value = "删除topic")
    public Response<TopicMessageStatusDTO> deleteTopic(@PathVariable String topicName, @Valid EnvRequest envRequest) {
        return success(rocketMQTopicService.deleteTopic(topicName, envRequest.toInstanceName()));
    }

    // todo 剩下一些订阅组的功能需要做
}
