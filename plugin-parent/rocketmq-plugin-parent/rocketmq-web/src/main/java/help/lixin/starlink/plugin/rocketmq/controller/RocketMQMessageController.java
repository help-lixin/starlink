package help.lixin.starlink.plugin.rocketmq.controller;

import static help.lixin.response.Response.success;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.response.Response;
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQMessageService;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/28 5:16 下午
 * @Description
 */
@RestController
@RequestMapping("/rocketmq/message")
public class RocketMQMessageController {

    @Autowired
    private IRocketMQMessageService rocketMQMessageService;

    @GetMapping("/{topicName}/query/topic")
    @ApiOperation(value = "根据topic与时间查询")
    public Response queryByTopic(@RequestParam(name = "begin") Long begin, //
        @RequestParam(name = "end") Long end, //
        @Valid EnvRequest envRequest, //
        @PathVariable String topicName) {
        return success(rocketMQMessageService.queryByTopic(begin, end, topicName, envRequest.toInstanceName()));
    }

    @ApiOperation(value = "根据topic与key查询")
    @GetMapping("/{topicName}/{key}/query/key")
    public Response queryByTopicAndKey(@PathVariable String key, //
        @Valid EnvRequest envRequest, //
        @PathVariable String topicName) {
        return success(rocketMQMessageService.queryByTopicAndKey(key, topicName, envRequest.toInstanceName()));
    }

    @ApiOperation(value = "根据topic与msgId查询")
    @GetMapping("/{topicName}/{msgId}/query/topic")
    public Response query(@Valid EnvRequest envRequest, @PathVariable String topicName, @PathVariable String msgId) {
        return success(rocketMQMessageService.queryByMessageId(msgId, topicName, envRequest.toInstanceName()));
    }
}
