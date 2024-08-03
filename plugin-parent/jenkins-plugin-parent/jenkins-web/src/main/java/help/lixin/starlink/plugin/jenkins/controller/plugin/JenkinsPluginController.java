package help.lixin.starlink.plugin.jenkins.controller.plugin;

import static help.lixin.response.Response.success;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import help.lixin.authorize.user.context.UserContext;
import help.lixin.response.Response;
import help.lixin.starlink.plugin.jenkins.convert.PluginControllerConvert;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsInstallPlugins;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsPluginInit;
import help.lixin.starlink.plugin.jenkins.dto.plugin.InstallPluginDTO;
import help.lixin.starlink.plugin.jenkins.dto.plugin.JenkinsInstallPluginsDTO;
import help.lixin.starlink.plugin.jenkins.dto.plugin.JenkinsPluginPageListDTO;
import help.lixin.starlink.plugin.jenkins.request.plugin.InstallPluginVO;
import help.lixin.starlink.plugin.jenkins.request.plugin.JenkinsInstallPluginsVO;
import help.lixin.starlink.plugin.jenkins.request.plugin.JenkinsPluginPageListVO;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPluginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/12 5:57 下午
 * @Description
 */
@RestController
@RequestMapping("/jenkins/plugin")
@Api(tags = "jenkins 插件相关")
public class JenkinsPluginController {

    @Autowired
    private IJenkinsPluginService jenkinsPluginService;

    @GetMapping("/installed/list")
    @ApiOperation(value = "查询插件已安装列表")
    public Response<List<JenkinsInstallPlugins>> queryList(JenkinsInstallPluginsVO jenkinsInstallPluginsVO) {
        PluginControllerConvert mapper = Mappers.getMapper(PluginControllerConvert.class);
        JenkinsInstallPluginsDTO jenkinsInstallPluginsDTO = mapper.convert(jenkinsInstallPluginsVO);
        return success(jenkinsPluginService.queryInstallPlugins(jenkinsInstallPluginsDTO));
    }

    @PostMapping("/init/add")
    @ApiOperation(value = "添加初始化插件")
    public Response<Integer> addInitPlugins(InstallPluginVO installPluginVO) {
        PluginControllerConvert mapper = Mappers.getMapper(PluginControllerConvert.class);
        InstallPluginDTO installPluginDTO = mapper.convert(installPluginVO);
        return success(jenkinsPluginService.addInitPlugins(installPluginDTO));
    }

    @GetMapping("/init/list")
    @ApiOperation(value = "查询初始化插件列表")
    public Response<List<JenkinsPluginInit>> queryInitPlugins(JenkinsPluginPageListVO jenkinsPluginPageListVO) {
        PluginControllerConvert mapper = Mappers.getMapper(PluginControllerConvert.class);
        JenkinsPluginPageListDTO jenkinsPluginPageListDTO = mapper.convert(jenkinsPluginPageListVO);
        return success(jenkinsPluginService.queryInitPlugins(jenkinsPluginPageListDTO));
    }

    @PutMapping("/init/changeStatus/{id}/{status}")
    @ApiOperation(value = "删除初始化表单插件")
    public Response<Integer> removeInitPlugin(@PathVariable Long id, @PathVariable Integer status) {
        return success(jenkinsPluginService.initPluginChangeStatus(id, status, UserContext.getUser().getUserName()));
    }

    @PostMapping("/install")
    @ApiOperation(value = "安装插件")
    public Response<Boolean> installPlugin(InstallPluginVO installPluginVO) {
        PluginControllerConvert mapper = Mappers.getMapper(PluginControllerConvert.class);
        InstallPluginDTO installPluginDTO = mapper.convert(installPluginVO);
        return success(jenkinsPluginService.installPlugin(installPluginDTO));
    }

    @DeleteMapping("/uninstall")
    @ApiOperation(value = "卸载插件")
    public Response<Integer> uninstallPlugin(Long id) {
        return success(jenkinsPluginService.uninstallPlugin(id));
    }
}
