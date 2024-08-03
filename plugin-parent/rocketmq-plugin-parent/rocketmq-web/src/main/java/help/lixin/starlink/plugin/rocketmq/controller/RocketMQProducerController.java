package help.lixin.starlink.plugin.rocketmq.controller;

import static help.lixin.response.Response.success;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import help.lixin.response.Response;
import help.lixin.starlink.plugin.rocketmq.api.dto.producer.ProducerInfoDTO;
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQProducerService;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;

/**
 * @Author: 伍岳林
 * @Date: 2023/9/11 4:24 下午
 * @Description
 */
@Api(tags = "生产者控制层")
@RestController
@RequestMapping("/rocketmq/producer")
public class RocketMQProducerController {

    @Autowired
    private IRocketMQProducerService rocketMQProducerService;

    @GetMapping("/{producerGroup}/{topic}/producer")
    public Response<List<ProducerInfoDTO>> producerConnection(@PathVariable String producerGroup, //
        @PathVariable String topic, //
        @Valid EnvRequest envRequest) {
        return success(rocketMQProducerService.producerConnection(producerGroup, topic, envRequest.toInstanceName()));
    }

}
