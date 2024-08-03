package help.lixin.starlink.plugin.jenkins.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.api.service.impl.PluginService;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsInstallPlugins;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsPluginInit;
import help.lixin.starlink.plugin.jenkins.dto.plugin.InstallPluginDTO;
import help.lixin.starlink.plugin.jenkins.dto.plugin.JenkinsInstallPluginsDTO;
import help.lixin.starlink.plugin.jenkins.dto.plugin.JenkinsPluginPageListDTO;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsInstallPluginsMapper;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsPluginInitMapper;
import help.lixin.starlink.plugin.jenkins.service.IJenkinsPluginService;
import help.lixin.response.PageResponse;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/12 3:26 下午
 * @Description
 */
public class JenkinsPluginService implements IJenkinsPluginService {

    private JenkinsPluginInitMapper jenkinsPluginInitMapper;
    private JenkinsInstallPluginsMapper jenkinsInstallPluginsMapper;
    private QueryTemplate queryTemplate;

    private final AbstractServiceFactory jenkinsServiceFactory;

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer addInitPlugins(InstallPluginDTO installPluginDTO) {
        JenkinsPluginInit jenkinsPluginInit = new JenkinsPluginInit();
        String pluginName = String.format("%s@%s", installPluginDTO.getPluginName(), installPluginDTO.getVersion());
        jenkinsPluginInit.setPluginName(pluginName);
        jenkinsPluginInit.setCreateBy(installPluginDTO.getCreateBy());
        return jenkinsPluginInitMapper.insert(jenkinsPluginInit);
    }

    @Override
    public PageResponse<JenkinsPluginInit> queryInitPlugins(JenkinsPluginPageListDTO jenkinsPluginPageListDTO) {
        return queryTemplate.execute(jenkinsPluginPageListDTO,()->{
            jenkinsPluginInitMapper.queryInitPlugin(jenkinsPluginPageListDTO);
        });
    }

    @Override
    public Integer initPluginChangeStatus(Long id, Integer status, String updateBy) {
        JenkinsPluginInit jenkinsPluginInit = jenkinsPluginInitMapper.selectById(id);
        if (jenkinsPluginInit == null){
            throw new ServiceException("该id不存在");
        }
        jenkinsPluginInit.setStatus(status);
        jenkinsPluginInit.setUpdateBy(updateBy);
        return jenkinsPluginInitMapper.updateById(jenkinsPluginInit);
    }

    @Override
    public Boolean installPlugin(InstallPluginDTO installPluginDTO) {
        PluginService pluginService = jenkinsServiceFactory.getInstance(installPluginDTO.getInstanceCode(), PluginService.class);
        pluginService.installPlugin(installPluginDTO.getPluginName(),installPluginDTO.getVersion());
        return true;
    }

    @Override
    public PageResponse<JenkinsInstallPlugins> queryInstallPlugins(JenkinsInstallPluginsDTO jenkinsInstallPluginsDTO) {
        return queryTemplate.execute(jenkinsInstallPluginsDTO, () -> {
            jenkinsInstallPluginsMapper.queryList(jenkinsInstallPluginsDTO);
        });
    }

    @Override
    public Integer uninstallPlugin(Long id) {
        return null;
    }

    @Override
    public String queryPluginInfo(String instanceCode, String pluginName) {
        Wrapper<JenkinsInstallPlugins> queryWrapper = new QueryWrapper<JenkinsInstallPlugins>() //
                .eq("instance_code", instanceCode) //
                .eq("plugin_name", pluginName) //
                .eq("status", 1);
        JenkinsInstallPlugins jenkinsInstallPlugin = jenkinsInstallPluginsMapper.selectOne(queryWrapper);
        String plugin = String.format("%s@%s", jenkinsInstallPlugin.getPluginName(), jenkinsInstallPlugin.getVersion());
        return plugin;
    }

    public JenkinsPluginService(
            AbstractServiceFactory jenkinsServiceFactory,
            QueryTemplate queryTemplate,
            JenkinsPluginInitMapper jenkinsPluginInitMapper,
            JenkinsInstallPluginsMapper jenkinsInstallPluginsMapper
    ) {
        this.jenkinsPluginInitMapper = jenkinsPluginInitMapper;
        this.jenkinsInstallPluginsMapper = jenkinsInstallPluginsMapper;
        this.jenkinsServiceFactory = jenkinsServiceFactory;
    }
}
