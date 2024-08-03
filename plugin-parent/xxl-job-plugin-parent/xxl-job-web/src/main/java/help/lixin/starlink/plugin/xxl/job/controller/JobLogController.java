package help.lixin.starlink.plugin.xxl.job.controller;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.xxl.job.api.dto.LogResult;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobInfo;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobLog;
import help.lixin.starlink.plugin.xxl.job.convert.JobLogConvert;
import help.lixin.starlink.plugin.xxl.job.dto.log.JobLogClearDTO;
import help.lixin.starlink.plugin.xxl.job.dto.log.JobLogDetailDTO;
import help.lixin.starlink.plugin.xxl.job.dto.log.JobLogPageListDTO;
import help.lixin.starlink.plugin.xxl.job.request.log.JobLogClearVO;
import help.lixin.starlink.plugin.xxl.job.request.log.JobLogDetailVO;
import help.lixin.starlink.plugin.xxl.job.request.log.JobLogPageListVO;
import help.lixin.starlink.plugin.xxl.job.service.IXxlJobLogService;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/6 2:49 下午
 * @Description
 */
@RestController
@RequestMapping("/job/log")
@Api(tags = "任务日志管理控制层")
public class JobLogController {

    @Autowired
    private IXxlJobLogService jobLogService;

    @GetMapping(value = "/{jobGroupId}")
    @ApiOperation(value = "查询组日志信息")
    public List<XxlJobInfo> getJobsByGroup(@Valid EnvRequest envRequest,
        @PathVariable("jobGroupId") Integer jobGroupId) {
        return jobLogService.queryById(envRequest.toInstanceName(), jobGroupId);
    }

    @GetMapping("/list")
    @ApiOperation(value = "分页查询调度日志")
    PageResponse<XxlJobLog> pageList(@Valid JobLogPageListVO jobLogPageListVO) {
        JobLogConvert jobLogConvert = Mappers.getMapper(JobLogConvert.class);
        JobLogPageListDTO jobLogPageListDTO = jobLogConvert.convert(jobLogPageListVO);
        jobLogPageListDTO.setInstanceName(jobLogPageListVO.getEnvRequest().toInstanceName());
        return jobLogService.pageList(jobLogPageListDTO);
    }

    @GetMapping("/info")
    @ApiOperation(value = "查看详情")
    public LogResult logDetailCat(@Valid JobLogDetailVO jobLogDetailVO) {
        JobLogConvert jobLogConvert = Mappers.getMapper(JobLogConvert.class);
        JobLogDetailDTO jobLogDetailDTO = jobLogConvert.convert(jobLogDetailVO);
        jobLogDetailDTO.setInstanceName(jobLogDetailVO.getEnvRequest().toInstanceName());
        return jobLogService.logDetailCat(jobLogDetailDTO);
    }

    @GetMapping("/kill")
    @ApiOperation(value = "停止执行")
    String logKill(@Valid EnvRequest envRequest, Integer id) {
        return jobLogService.logKill(envRequest.toInstanceName(), id);
    }

    @GetMapping("/clear")
    @ApiOperation(value = "清理日志")
    public String clearLog(JobLogClearVO jobLogClearVO) {
        JobLogConvert jobLogConvert = Mappers.getMapper(JobLogConvert.class);
        JobLogClearDTO jobLogClearDTO = jobLogConvert.convert(jobLogClearVO);
        jobLogClearDTO.setInstanceName(jobLogClearVO.getEnvRequest().toInstanceName());
        return jobLogService.clearLog(jobLogClearDTO);
    }
}
