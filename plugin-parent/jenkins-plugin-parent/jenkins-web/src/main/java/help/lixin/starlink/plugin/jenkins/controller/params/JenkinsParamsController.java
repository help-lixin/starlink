package help.lixin.starlink.plugin.jenkins.controller.params;

import static help.lixin.response.Response.success;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.jenkins.convert.ParamControllerConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsParams;
import help.lixin.starlink.plugin.jenkins.dto.params.JenkinsParamDTO;
import help.lixin.starlink.plugin.jenkins.request.params.JenkinsParamVO;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsParamsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/jenkins/param")
@Api(tags = "jenkins Params Setup")
public class JenkinsParamsController {

    @Autowired
    private IJenkinsParamsService jenkinsParamsService;

    @PostMapping("/add")
    @ApiOperation(value = "添加param")
    public Response<Integer> addParam(JenkinsParamVO paramVO) {
        ParamControllerConvert mapper = Mappers.getMapper(ParamControllerConvert.class);
        JenkinsParamDTO paramDTO = mapper.convert(paramVO);
        paramDTO.setCreateBy(UserContext.getUser().getUserName());
        return success(jenkinsParamsService.saveParams(paramDTO));
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "查询params信息")
    public Response<JenkinsParams> queryParamInfo(@PathVariable Long id) {
        return success(jenkinsParamsService.paramInfo(id));
    }

    @PostMapping("/changeStatus/{id}/{status}")
    @ApiOperation(value = "修改状态")
    public Response<Integer> removeInitPlugin(@PathVariable Long id, @PathVariable Integer status) {
        return success(jenkinsParamsService.changeStatus(id, status, UserContext.getUser().getUserName()));
    }
}
