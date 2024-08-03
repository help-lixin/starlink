package help.lixin.starlink.plugin.rocketmq.controller;

import static help.lixin.response.Response.success;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import help.lixin.response.Response;
import help.lixin.starlink.plugin.rocketmq.service.IRocketMQDashboardService;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 4:37 下午
 * @Description
 */
@Api(tags = "仪表盘控制层")
@RestController
@RequestMapping("/rocketmq/dashboard")
public class RocketMQDashboardController {

    @Autowired
    private IRocketMQDashboardService rocketDashboardService;

    @GetMapping("/topicCurrent")
    @ApiOperation(value = "查询相关topic")
    public Response<List<String>> topicCurrent(@Valid EnvRequest envRequest) {
        return success(rocketDashboardService.topicCurrent(envRequest.toInstanceName()));
    }

    @GetMapping("/queryBroker")
    @ApiOperation(value = "查询当前broker信息")
    public Response<Map<String, List<String>>> queryBroker(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date queryDate, @Valid EnvRequest envRequest) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(queryDate);
        return success(rocketDashboardService.queryBroker(formattedDate, envRequest.toInstanceName()));
    }

    @GetMapping("/queryTopic")
    @ApiOperation(value = "查询当前topic信息")
    public Response<List<String>> queryTopic(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date queryDate,
        @RequestParam String topicName, @Valid EnvRequest envRequest) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(queryDate);

        return success(rocketDashboardService.queryTopic(formattedDate, topicName, envRequest.toInstanceName()));
    }
}
