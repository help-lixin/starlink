package help.lixin.starlink.plugin.jenkins.controller.job;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.authorize.user.context.UserContext;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.convert.JobControllerConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsJob;
import help.lixin.starlink.plugin.jenkins.dto.job.BuildDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobFormDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobFormDetailDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobPageListDTO;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupCommonDTO;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsJobService;
import help.lixin.starlink.plugin.jenkins.request.job.BuildVO;
import help.lixin.starlink.plugin.jenkins.request.job.CheckCredentialVO;
import help.lixin.starlink.plugin.jenkins.request.job.JenkinsJobFormVO;
import help.lixin.starlink.plugin.jenkins.request.job.JenkinsJobPageListVO;
import help.lixin.response.PageResponse;
import help.lixin.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static help.lixin.response.Response.success;

@RestController
@RequestMapping("/jenkins/job")
@Api(tags = "jenkins 任务表单")
public class JenkinsJobController {

    @Autowired
    private IJenkinsJobService jenkinsJobService;


    @GetMapping("/info/{id}")
    @ApiOperation(value = "查询job信息")
    public Response<JenkinsJob> queryJobInfo(@PathVariable Long id){
        return success(jenkinsJobService.queryJobInfo(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询任务列表信息")
    public Response<PageResponse<JenkinsJob>> pageList(JenkinsJobPageListVO jenkinsJobPageListVO){
        JobControllerConvert mapper = Mappers.getMapper(JobControllerConvert.class);
        JenkinsJobPageListDTO jenkinsJobPageListDTO = mapper.convert(jenkinsJobPageListVO);
        return success(jenkinsJobService.pageList(jenkinsJobPageListDTO));
    }

    @PutMapping("/changeStatus/{id}/{status}")
    @ApiOperation(value = "修改状态")
    public Response<Integer> removeInitPlugin(@PathVariable Long id,@PathVariable Integer status){
        return success(jenkinsJobService.changeStatus(id, status, UserContext.getUser().getUserName()));
    }

    @PostMapping("/add")
    @ApiOperation(value = "创建任务")
    public Response<Boolean> addJob(@RequestBody @Valid JenkinsJobFormVO jenkinsJobFormVO){
        if (jenkinsJobFormVO.getId() == null && !jenkinsJobService.jobNameIsExist(jenkinsJobFormVO.getJobName(),
                jenkinsJobFormVO.getInstanceCode())) {
            throw new ServiceException("名称已存在,请确认后修改");
        }
        JobControllerConvert mapper = Mappers.getMapper(JobControllerConvert.class);
        JenkinsJobFormDTO jenkinsJobDTO = mapper.convert(jenkinsJobFormVO);
        jenkinsJobDTO.setCreateBy(UserContext.getUser().getUserName());

        try {
            // TODO
            // 由于vo转dto时,泛型不支持,所以,先进行手工转换
            // 创建一个 ObjectMapper 对象
            ObjectMapper objectMapper = new ObjectMapper();
            // 把 List 转换成 JSON 字符串
            String json = objectMapper.writeValueAsString(jenkinsJobFormVO.getSetups());
            // 把 JSON 字符串转换成 List 对象
            List<JenkinsPipelineSetupCommonDTO> setupList = objectMapper.readValue(json, new TypeReference<>() {
            });
            jenkinsJobDTO.setSetups(setupList);
        } catch (JsonProcessingException e) {
            throw new ServiceException("转换json出现异常："+e.getMessage());
        }

        return success(jenkinsJobService.saveJob(jenkinsJobDTO));
    }

    @DeleteMapping("/del/{jobId}")
    @ApiOperation(value = "删除jenkinsJob")
    public Response<Boolean> removeJob(@PathVariable Long jobId) {
        return success(jenkinsJobService.removeJob(jobId));
    }

    @GetMapping("/detail/{jobId}")
    @ApiOperation(value = "查询job详情信息")
    public Response<JenkinsJobFormDetailDTO> queryJobDetail(@PathVariable Long jobId) {
        return success(jenkinsJobService.queryJobDetail(jobId));
    }

    @GetMapping("/jobOption/{instanceCode}")
    @ApiOperation(value = "查询依赖构建下拉列表")
    public Response<List<String>> querySetupList(@PathVariable String instanceCode) {
        return success(jenkinsJobService.queryJobOptionList(instanceCode));
    }

    @GetMapping("/buildJob")
    @ApiOperation(value = "开始构建任务")
    public Response<Boolean> buildJob(@Valid BuildVO buildVO) {
        JobControllerConvert mapper = Mappers.getMapper(JobControllerConvert.class);
        BuildDTO buildDTO = mapper.convert(buildVO);
        buildDTO.setCreateBy(UserContext.getUser().getUserName());
        return success(jenkinsJobService.buildJob(buildDTO));
    }

    @GetMapping("/jobNameIsExist/{jobName}/{instanceCode}")
    @ApiOperation(value = "查询任务名称是否可用")
    public Response<Boolean> jobNameIsExist(@PathVariable String jobName,@PathVariable String instanceCode) {
        return success(jenkinsJobService.jobNameIsExist(jobName, instanceCode));
    }

    @PatchMapping("/checkCredentialsId")
    @ApiOperation(value = "获取校验远程仓库是否存在的结果")
    public void checkCredentialsId(CheckCredentialVO checkCredentialVO) {
        jenkinsJobService.checkCredentialsId(checkCredentialVO.getInstanceCode(),
                checkCredentialVO.getCredentialsId(),checkCredentialVO.getPath(),checkCredentialVO.getJobName());
    }
}
