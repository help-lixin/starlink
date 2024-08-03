package help.lixin.starlink.plugin.jenkins.controller.build;

import static help.lixin.response.Response.success;

import java.util.List;

import javax.validation.Valid;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.jenkins.convert.SystemConfigControllerConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsSystemConfig;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsSystemConfigPageDTO;
import help.lixin.starlink.plugin.jenkins.dto.sys.JenkinsSystemConfigDTO;
import help.lixin.starlink.plugin.jenkins.dto.systool.OptionDTO;
import help.lixin.starlink.plugin.jenkins.request.build.JenkinsCheckHomeVO;
import help.lixin.starlink.plugin.jenkins.request.build.JenkinsCheckNameVO;
import help.lixin.starlink.plugin.jenkins.request.build.JenkinsSystemConfigPageVO;
import help.lixin.starlink.plugin.jenkins.request.build.JenkinsSystemConfigVO;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsSystemConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/jenkins/systemConfig/")
@Api(tags = "jenkins 系统工具配置")
public class JenkinsPipelineSystemConfigController {

    @Autowired
    private IJenkinsSystemConfigService jenkinsSystemConfigService;

    @PostMapping("/add")
    @ApiOperation(value = "添加systemConfig")
    public Response<Boolean> addSystemConfig(@RequestBody @Valid JenkinsSystemConfigVO jenkinsConfigVO) {
        SystemConfigControllerConvert mapper = Mappers.getMapper(SystemConfigControllerConvert.class);
        JenkinsSystemConfigDTO jenkinsSystemConfigDTO = mapper.convert(jenkinsConfigVO);
        jenkinsSystemConfigDTO.setCreateBy(UserContext.getUser().getUserName());
        return success(jenkinsSystemConfigService.saveConfig(jenkinsSystemConfigDTO));
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询systemConfig分页列表")
    public Response<JenkinsSystemConfig>
        querySystemConfigInfo(@Valid JenkinsSystemConfigPageVO jenkinsSystemConfigPageVO) {
        SystemConfigControllerConvert mapper = Mappers.getMapper(SystemConfigControllerConvert.class);
        JenkinsSystemConfigPageDTO jenkinsSystemConfigPageDTO = mapper.convert(jenkinsSystemConfigPageVO);
        return success(jenkinsSystemConfigService.list(jenkinsSystemConfigPageDTO));
    }

    @GetMapping("/syncAllSysConfig")
    @ApiOperation(value = "同步所有系统配置")
    public Response<JenkinsSystemConfig> syncAllSysConfig() {
        return success(jenkinsSystemConfigService.syncAllSysConfig());
    }

    @GetMapping("/info/{id}")
    @ApiOperation(value = "查询systemConfig信息")
    public Response<JenkinsSystemConfig> querySystemConfigInfo(@PathVariable Long id) {
        return success(jenkinsSystemConfigService.querySystemConfigInfo(id));
    }

    @PostMapping("/changeStatus/{id}/{status}")
    @ApiOperation(value = "修改状态")
    public Response<Integer> removeInitPlugin(@PathVariable Long id, @PathVariable Integer status) {
        return success(jenkinsSystemConfigService.changeStatus(id, status, UserContext.getUser().getUserName()));
    }

    @GetMapping("/checkHome")
    @ApiOperation(value = "检查服务器路径是否存在")
    public Response<Boolean> checkHome(@Valid JenkinsCheckHomeVO jenkinsCheckHomeVO) {
        return success(jenkinsSystemConfigService.checkHome(jenkinsCheckHomeVO.getJenkinsManageToolsModule(),
            jenkinsCheckHomeVO.getHomePath(), jenkinsCheckHomeVO.getInstanceCode()));
    }

    @GetMapping("/checkName")
    @ApiOperation(value = "检查名称是否唯一存在")
    public Response<Boolean> checkName(@Valid JenkinsCheckNameVO jenkinsCheckNameVO) {
        return success(jenkinsSystemConfigService.checkName(jenkinsCheckNameVO.getJenkinsManageToolsModule(),
            jenkinsCheckNameVO.getName(), jenkinsCheckNameVO.getInstanceCode()));
    }

    @GetMapping("/optionSelect/{pluginType}/{instanceCode}")
    @ApiOperation(value = "根据工具类型查询相关列表信息")
    public Response<List<OptionDTO>> optionSelect(@PathVariable String pluginType, @PathVariable String instanceCode) {
        return success(jenkinsSystemConfigService.queryByPluginType(pluginType, instanceCode));
    }

    @GetMapping("/pluginType/optionSelect/{instanceCode}")
    @ApiOperation(value = "查询已存在的工具类型")
    public Response<List<OptionDTO>> optionSelectPluginType(@PathVariable String instanceCode) {
        return success(jenkinsSystemConfigService.queryPluginTypeByInstanceCode(instanceCode));
    }

    @DeleteMapping("/del/{id}")
    @ApiOperation("删除系统配置")
    public Response<Boolean> deleteConfig(@PathVariable Long id) {
        return success(jenkinsSystemConfigService.deleteConfig(id));
    }
}