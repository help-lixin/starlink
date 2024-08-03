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

import help.lixin.exception.ServiceException;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jenkins.api.model.PluginSearchResultInfo;
import help.lixin.starlink.plugin.jenkins.api.service.IPluginService;
import help.lixin.starlink.plugin.jenkins.api.service.ISafeRestartService;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsInstallPlugins;
import help.lixin.starlink.plugin.jenkins.job.service.IJenkinsRetryInstallPluginService;
import help.lixin.starlink.plugin.jenkins.mapper.JenkinsInstallPluginsMapper;

/**
 * @Author: 伍岳林
 * @Date: 2024/3/7 上午10:07
 * @Description
 */
public class JenkinsRetryInstallPluginService implements IJenkinsRetryInstallPluginService {

    private Logger logger = LoggerFactory.getLogger(JenkinsRetryInstallPluginService.class);
    private JenkinsInstallPluginsMapper jenkinsInstallPluginsMapper;

    private final AbstractServiceFactory jenkinsServiceFactory;

    /**
     * @Author: 伍岳林
     * @Date: 2024/3/7 上午9:55
     * @Return: void
     * @Description 重试插件安装
     */
    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public void retryInstallPlugin() {
        // 获取插件列表
        Set<String> contextNames = jenkinsServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            return;
        }

        // 遍历插件列表
        contextNames.forEach(instanceCode -> {
            IPluginService pluginService = jenkinsServiceFactory.getInstance(instanceCode, IPluginService.class);
            ISafeRestartService safeRestartService =
                jenkinsServiceFactory.getInstance(instanceCode, ISafeRestartService.class);
            List<JenkinsInstallPlugins> jenkinsInstallPluginsList =
                jenkinsInstallPluginsMapper.queryRetryPlugin(instanceCode);

            if (CollectionUtils.isEmpty(jenkinsInstallPluginsList)) {
                return;
            }

            Integer installAmount = 0;

            // 安装没有被安装上的插件
            for (JenkinsInstallPlugins jenkinsInstallPlugin : jenkinsInstallPluginsList) {

                String pluginName = jenkinsInstallPlugin.getPluginName();
                List<PluginSearchResultInfo> searchPlugins = pluginService.searchPlugin(pluginName, 1);

                if (CollectionUtils.isEmpty(searchPlugins)) {
                    savePlugin(jenkinsInstallPlugin);
                    continue;
                }

                PluginSearchResultInfo pluginSearchResultInfo = searchPlugins.get(0);

                // 完全匹配上初始化插件名称的插件则安装
                if (jenkinsInstallPlugin.getPluginName().equals(pluginSearchResultInfo.getName())
                    && jenkinsInstallPlugin.getVersion().equals(pluginSearchResultInfo.getVersion())) {
                    savePlugin(jenkinsInstallPlugin);
                    pluginService.installPlugin(pluginName, jenkinsInstallPlugin.getVersion());
                    installAmount++;
                } else {
                    savePlugin(jenkinsInstallPlugin);
                }

            }

            if (installAmount > 0) {
                // 重启jenkins
                logger.info("开始重启jenkins，安装插件数:[{}]", installAmount);
                safeRestartService.safeRestart();
            }

        });

    }

    /**
     * @Author: 伍岳林
     * @Date: 2024/3/7 下午4:54
     * @Return: void
     * @Description 查询并更新插件安装结果
     */
    @Override
    public void updatePluginInstallResult() {
        // 获取插件列表
        Set<String> contextNames = jenkinsServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            return;
        }

        // 遍历插件列表
        contextNames.forEach(instanceCode -> {
            IPluginService pluginService = jenkinsServiceFactory.getInstance(instanceCode, IPluginService.class);

            // 查询符合重试条件的插件
            List<JenkinsInstallPlugins> jenkinsInstallPluginsList =
                jenkinsInstallPluginsMapper.queryRetryPlugin(instanceCode);

            if (CollectionUtils.isEmpty(jenkinsInstallPluginsList)) {
                return;
            }

            jenkinsInstallPluginsList.forEach(installPlugins -> {
                List<PluginSearchResultInfo> pluginSearchResultInfos =
                    pluginService.searchPlugin(installPlugins.getPluginName(), 1);

                // 结果为空则把状态修改成 "已完成"
                if (CollectionUtils.isEmpty(pluginSearchResultInfos)) {
                    installPlugins.setInstallStatus(1);
                    installPlugins.setUpdateTime(new Date());
                    jenkinsInstallPluginsMapper.updateById(installPlugins);
                } else if (installPlugins.getPluginName().equals(pluginSearchResultInfos.get(0).getName())
                    && installPlugins.getVersion().equals(pluginSearchResultInfos.get(0).getVersion())) {

                    // 记录状态为 "失败"
                    installPlugins.setInstallStatus(0);
                    installPlugins.setUpdateTime(new Date());
                    jenkinsInstallPluginsMapper.updateById(installPlugins);
                } else {
                    Map<String, String> installedMap = pluginService.installedPlugins().stream()
                        .collect(Collectors.toMap(v -> v.getShortName(), v -> v.getVersion()));
                    if (installedMap.containsKey(installPlugins.getPluginName())
                        && installedMap.get(installPlugins.getPluginName()).equals(installPlugins.getVersion())) {
                        // 安装成功
                        installPlugins.setInstallStatus(1);
                        installPlugins.setUpdateTime(new Date());
                        jenkinsInstallPluginsMapper.updateById(installPlugins);
                    }
                }

            });
        });
    }

    private void savePlugin(JenkinsInstallPlugins jenkinsPluginInit) {
        jenkinsPluginInit.setRetryAmount(jenkinsPluginInit.getRetryAmount() + 1);
        jenkinsPluginInit.setInstallStatus(-1);
        jenkinsPluginInit.setUpdateTime(new Date());
        jenkinsInstallPluginsMapper.updateById(jenkinsPluginInit);
    }

    public JenkinsRetryInstallPluginService(AbstractServiceFactory jenkinsServiceFactory,
        JenkinsInstallPluginsMapper jenkinsInstallPluginsMapper) {
        this.jenkinsInstallPluginsMapper = jenkinsInstallPluginsMapper;
        this.jenkinsServiceFactory = jenkinsServiceFactory;
    }
}
