package help.lixin.starlink.plugin.jenkins.job.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jenkins.api.model.PluginSearchResultInfo;
import help.lixin.starlink.plugin.jenkins.api.service.IPluginService;
import help.lixin.starlink.plugin.jenkins.api.service.ISafeRestartService;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsInstallPlugins;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsPluginInit;
import help.lixin.starlink.plugin.jenkins.job.service.IJenkinsInstallPluginJobService;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsInstallPluginsMapper;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsPluginInitMapper;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/12 上午10:46
 * @Description
 */
public class JenkinsInstallPluginJobService implements IJenkinsInstallPluginJobService {

    private Logger logger = LoggerFactory.getLogger(JenkinsInstallPluginJobService.class);
    private JenkinsPluginInitMapper jenkinsPluginInitMapper;
    private JenkinsInstallPluginsMapper jenkinsInstallPluginsMapper;

    private final AbstractServiceFactory jenkinsServiceFactory;

    /**
     * @Author: 伍岳林
     * @Date: 2024/3/7 上午9:55
     * @Return: void
     * @Description 初始化插件安装
     */
    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public void checkInitPlugin() {
        // 获取插件列表
        Set<String> contextNames = jenkinsServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            return;
        }

        // 遍历插件列表
        contextNames.forEach(instanceCode -> {
            logger.info("检测jenkins插件安装情况");
            IPluginService pluginService = jenkinsServiceFactory.getInstance(instanceCode, IPluginService.class);
            ISafeRestartService safeRestartService =
                jenkinsServiceFactory.getInstance(instanceCode, ISafeRestartService.class);
            List<JenkinsPluginInit> jenkinsPluginInitList = jenkinsPluginInitMapper.selectList(new QueryWrapper<>());

            QueryWrapper<JenkinsInstallPlugins> installQueryWrapper = new QueryWrapper<>();
            installQueryWrapper.eq("instance_code", instanceCode);
            List<JenkinsInstallPlugins> jenkinsInstallPluginsList =
                jenkinsInstallPluginsMapper.selectList(installQueryWrapper);
            Map<String, JenkinsInstallPlugins> installedMap = jenkinsInstallPluginsList.stream()
                .collect(Collectors.toMap(JenkinsInstallPlugins::getPluginName, v -> v));

            // 选出没安装的初始化插件列表
            List<JenkinsPluginInit> diffList = jenkinsPluginInitList.stream()
                .filter(v -> !installedMap.containsKey(v.getPluginName())).collect(Collectors.toList());

            if (CollectionUtils.isEmpty(diffList)) {
                logger.info("jenkins插件安装情况正常，无需安装");
                return;
            }

            // 安装没有被安装上的插件
            diffList.forEach(v -> {
                List<PluginSearchResultInfo> searchPlugins = pluginService.searchPlugin(v.getPluginName(), 10);
                // 匹配并安装对应的插件
                searchPlugins.forEach(pluginSearchResult -> {
                    String pluginName = pluginSearchResult.getName();

                    // 完全匹配上初始化插件名称的插件则安装
                    if (v.getPluginName().equals(pluginName)) {
                        String pluginVersion = pluginSearchResult.getVersion();

                        savePlugin(instanceCode, pluginSearchResult);
                        pluginService.installPlugin(pluginName, pluginVersion);
                        return;
                    }
                });
            });

            // 重启jenkins
            if (diffList.size() > 0) {
                logger.info("开始重启jenkins，安装插件数:[{}]", diffList.size());
                safeRestartService.safeRestart();
            }

        });

    }

    private void savePlugin(String instanceCode, PluginSearchResultInfo v) {
        JenkinsInstallPlugins jenkinsInstallPlugins = new JenkinsInstallPlugins();
        jenkinsInstallPlugins.setInstanceCode(instanceCode);
        jenkinsInstallPlugins.setPluginName(v.getName());
        jenkinsInstallPlugins.setRetryAmount(0);
        jenkinsInstallPlugins.setStatus(1);
        jenkinsInstallPlugins.setInstallStatus(-1);
        jenkinsInstallPlugins.setVersion(v.getVersion());
        jenkinsInstallPlugins.setCreateTime(new Date());
        jenkinsInstallPlugins.setUpdateTime(new Date());
        jenkinsInstallPluginsMapper.insert(jenkinsInstallPlugins);
    }

    public JenkinsInstallPluginJobService(JenkinsPluginInitMapper jenkinsPluginInitMapper,
        JenkinsInstallPluginsMapper jenkinsInstallPluginsMapper, AbstractServiceFactory jenkinsServiceFactory) {
        this.jenkinsPluginInitMapper = jenkinsPluginInitMapper;
        this.jenkinsInstallPluginsMapper = jenkinsInstallPluginsMapper;
        this.jenkinsServiceFactory = jenkinsServiceFactory;
    }
}
