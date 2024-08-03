package help.lixin.starlink.plugin.jenkins.controller.log;

import help.lixin.starlink.plugin.jenkins.service.IJenkinsLogsService;
import help.lixin.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static help.lixin.response.Response.success;

@RestController
@RequestMapping("/jenkins/logs")
@Api(tags = "jenkins 构建日志")
public class JenkinsLogsController {

    @Autowired
    private IJenkinsLogsService jenkinsLogsService;


    @GetMapping("/info/{buildId}")
    @ApiOperation(value = "根据任务id查询构建日志信息")
    public Response<String> queryLogInfo(@PathVariable Long buildId){
        return success(jenkinsLogsService.queryLogInfo(buildId));
    }

}
