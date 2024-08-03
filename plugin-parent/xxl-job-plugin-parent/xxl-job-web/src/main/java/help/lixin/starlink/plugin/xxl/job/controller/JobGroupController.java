package help.lixin.starlink.plugin.xxl.job.controller;

import static help.lixin.response.Response.success;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.xxl.job.api.model.XxlJobGroup;
import help.lixin.starlink.plugin.xxl.job.convert.JobGroupConvert;
import help.lixin.starlink.plugin.xxl.job.request.group.JobGroupCreateVO;
import help.lixin.starlink.plugin.xxl.job.request.group.JobGroupUpdateVO;
import help.lixin.starlink.plugin.xxl.job.service.IXxlJobGroupService;
import help.lixin.starlink.request.EnvRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/6 2:48 下午
 * @Description
 */
@Api(tags = "执行器管理控制层")
@RequestMapping("/job/group")
@RestController
public class JobGroupController {

    @Autowired
    private IXxlJobGroupService jobGroupService;

    @GetMapping("/list")
    @ApiOperation(value = "执行器分页查询")
    public Response<PageResponse<XxlJobGroup>> pageList(@Valid EnvRequest envRequest, int start, int length,
        String appname, String title) {
        return Response.success(jobGroupService.query(envRequest.toInstanceName(), start, length, appname, title));
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加执行器")
    public Response<String> create(@Valid @RequestBody JobGroupCreateVO jobGroupCreateVO) {
        JobGroupConvert jobGroupConvert = Mappers.getMapper(JobGroupConvert.class);
        XxlJobGroup xxlJobGroup = jobGroupConvert.convert(jobGroupCreateVO);
        return success(jobGroupService.save(jobGroupCreateVO.getEnvRequest().toInstanceName(), xxlJobGroup));
    }

    @PutMapping("/edit")
    @ApiOperation(value = "执行器更新")
    public Response<String> update(@Valid @RequestBody JobGroupUpdateVO jobGroupUpdateVO) {
        JobGroupConvert jobGroupConvert = Mappers.getMapper(JobGroupConvert.class);
        XxlJobGroup xxlJobGroup = jobGroupConvert.convert(jobGroupUpdateVO);
        return success(jobGroupService.update(jobGroupUpdateVO.getEnvRequest().toInstanceName(), xxlJobGroup));
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除执行器")
    public Response<String> remove(@Valid EnvRequest envRequest, @PathVariable("id") Integer id) {
        return success(jobGroupService.remove(envRequest.toInstanceName(), id));
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "执行器查询详情")
    public Response<XxlJobGroup> loadById(@Valid EnvRequest envRequest, @PathVariable("id") Integer id) {
        return success(jobGroupService.loadById(envRequest.toInstanceName(), id));
    }
}
