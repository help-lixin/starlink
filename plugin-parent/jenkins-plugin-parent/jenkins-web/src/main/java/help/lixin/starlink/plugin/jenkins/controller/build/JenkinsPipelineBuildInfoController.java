package help.lixin.starlink.plugin.jenkins.controller.build;

import help.lixin.starlink.plugin.jenkins.convert.BuildInfoControllerConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsBuildInfo;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsBuildPageListDTO;
import help.lixin.starlink.plugin.jenkins.request.build.JenkinsBuildPageListVO;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsBuildInfoService;
import help.lixin.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static help.lixin.response.Response.success;

@RestController
@RequestMapping("/jenkins/build")
@Api(tags = "jenkins 构建信息")
public class JenkinsPipelineBuildInfoController {

    @Autowired
    private IJenkinsBuildInfoService jenkinsBuildInfoService;


//    @PostMapping("/add")
//    @ApiOperation(value = "添加buildInfo")
//    public Response<Integer> addBuildInfo(JenkinsBuildInfoVO jenkinsBuildInfoVO){
//        BuildInfoControllerConvert mapper = Mappers.getMapper(BuildInfoControllerConvert.class);
//        JenkinsBuildInfoDTO jenkinsBuildInfoDTO = mapper.convert(jenkinsBuildInfoVO);
//        return success(jenkinsBuildInfoService.saveBuildIfo(jenkinsBuildInfoDTO));
//    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "查询buildInfo信息")
    public Response<JenkinsBuildInfo> queryBuildInfoInfo(@PathVariable Long id){
        return success(jenkinsBuildInfoService.queryBuildInfo(id));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询buildInfo分页信息")
    public Response<JenkinsBuildInfo> pageList(JenkinsBuildPageListVO jenkinsBuildPageListVO){
        BuildInfoControllerConvert mapper = Mappers.getMapper(BuildInfoControllerConvert.class);
        JenkinsBuildPageListDTO jenkinsBuildPageListDTO = mapper.convert(jenkinsBuildPageListVO);
        return success(jenkinsBuildInfoService.pageList(jenkinsBuildPageListDTO));
    }

//    @PostMapping("/changeStatus/{id}/{status}")
//    @ApiOperation(value = "修改状态")
//    public Response<Integer> removeInitPlugin(@PathVariable Long id,@PathVariable Integer status){
//        return success(jenkinsBuildInfoService.changeStatus(id, status, UserContext.getUser().getUserName()));
//    }
}