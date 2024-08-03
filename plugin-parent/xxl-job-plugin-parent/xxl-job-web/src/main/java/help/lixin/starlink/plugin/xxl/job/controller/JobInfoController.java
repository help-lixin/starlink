package help.lixin.starlink.plugin.xxl.job.controller;

import static help.lixin.response.Response.success;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.xxl.job.api.dto.JobInfoResponse;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobInfo;
import help.lixin.starlink.plugin.xxl.job.convert.JobInfoConvert;
import help.lixin.starlink.plugin.xxl.job.dto.JobInfoQueryDTO;
import help.lixin.starlink.plugin.xxl.job.request.info.*;
import help.lixin.starlink.plugin.xxl.job.service.IXxlJobInfoService;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/6 2:49 下午
 * @Description
 */
@RequestMapping("/job/info")
@RestController
@Api(tags = "任务管理控制层")
public class JobInfoController {

    @Autowired
    private IXxlJobInfoService jobInfoService;

    @GetMapping("/list")
    @ApiOperation(value = "分页查询")
    public Response<PageResponse<JobInfoResponse>> pageList(@Valid EnvRequest envRequest,
        @Valid JobInfoQueryVO jobInfoQueryVO) {
        JobInfoConvert jobInfoConvert = Mappers.getMapper(JobInfoConvert.class);
        JobInfoQueryDTO jobInfoQueryDTO = jobInfoConvert.convert(jobInfoQueryVO);
        return success(jobInfoService.pageList(envRequest.toInstanceName(), jobInfoQueryDTO));
    }

    @PostMapping("/add")
    @ApiOperation(value = "新建任务")
    public Response<String> create(@Valid @RequestBody JobInfoCreateVO jobInfoCreateVO) {
        JobInfoConvert jobInfoConvert = Mappers.getMapper(JobInfoConvert.class);
        XxlJobInfo xxlJobInfo = jobInfoConvert.convert(jobInfoCreateVO);
        return success(jobInfoService.create(jobInfoCreateVO.getEnvRequest().toInstanceName(), xxlJobInfo));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "更新任务")
    public Response<String> update(@Valid @RequestBody JobInfoUpdateVO jobInfoUpdateVO) {
        JobInfoConvert jobInfoConvert = Mappers.getMapper(JobInfoConvert.class);
        XxlJobInfo xxlJobInfo = jobInfoConvert.convert(jobInfoUpdateVO);
        return success(jobInfoService.update(jobInfoUpdateVO.getEnvRequest().toInstanceName(), xxlJobInfo));
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除任务")
    public Response<String> remove(@Valid EnvRequest envRequest, @PathVariable("id") Integer id) {
        return success(jobInfoService.remove(envRequest.toInstanceName(), id));
    }

    @ApiOperation(value = "停止任务")
    @GetMapping("/stop/{id}")
    public Response pause(@Valid EnvRequest envRequest, @PathVariable("id") Integer id) {
        return success(jobInfoService.pause(envRequest.toInstanceName(), id));
    }

    @ApiOperation(value = "启动任务")
    @GetMapping("/start/{id}")
    public Response<String> start(@Valid EnvRequest envRequest, @PathVariable("id") Integer id) {
        return success(jobInfoService.start(envRequest.toInstanceName(), id));
    }

    @ApiOperation(value = "执行一次任务")
    @PostMapping("/trigger/job")
    public Response<String> triggerJob(@Valid @RequestBody JobInfoTriggerJobVO jobInfoTriggerJobVO) {
        return success(jobInfoService.triggerJob(jobInfoTriggerJobVO.getEnvRequest().toInstanceName(),
            jobInfoTriggerJobVO.getId(), jobInfoTriggerJobVO.getExecutorParam(), jobInfoTriggerJobVO.getAddressList()));
    }

    @ApiOperation(value = "下一次执行时间")
    @PostMapping("/next/trigger/time")
    public Response<List<String>>
        nextTriggerTime(@Valid @RequestBody JobInfoNextTriggerTimeVO jobInfoNextTriggerTimeVO) {
        return success(jobInfoService.nextTriggerTime(jobInfoNextTriggerTimeVO.getEnvRequest().toInstanceName(),
            jobInfoNextTriggerTimeVO.getScheduleType(), jobInfoNextTriggerTimeVO.getScheduleConf()));

    }
}
