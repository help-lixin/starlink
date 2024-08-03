package help.lixin.starlink.plugin.jenkins.api.service.impl;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.crumb.Crumb;
import com.cdancy.jenkins.rest.domain.plugins.Plugins;
import com.cdancy.jenkins.rest.features.PluginManagerApi;
import help.lixin.starlink.plugin.jenkins.api.model.Plugin;
import help.lixin.starlink.plugin.jenkins.api.model.PluginSearchResultInfo;
import help.lixin.starlink.plugin.jenkins.api.service.ICrumbIssuerService;
import help.lixin.starlink.plugin.jenkins.api.service.IPluginService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PluginService implements IPluginService {

    private Logger logger = LoggerFactory.getLogger(PluginService.class);

    private ICrumbIssuerService crumbIssuerService;
    private JenkinsClient jenkinsClient;

    private static final String SEARCH_PLUGIN_PATH = "/pluginManager/pluginsSearch?query=%s&limit=%s&_=%d";

    public PluginService(ICrumbIssuerService crumbIssuerService, JenkinsClient jenkinsClient) {
        this.crumbIssuerService = crumbIssuerService;
        this.jenkinsClient = jenkinsClient;
    }

    @Override
    public List<Plugin> installedPlugins() {
        PluginManagerApi pluginManagerApi = jenkinsClient.api().pluginManagerApi();
        Plugins pluginsResult = pluginManagerApi.plugins(3, null);
        List<com.cdancy.jenkins.rest.domain.plugins.Plugin> plugins = pluginsResult.plugins();
        List<Plugin> resultPlugins = plugins.stream().map(jenkinsPlugin -> {
                    Plugin plugin = new Plugin();
                    plugin.setActive(jenkinsPlugin.active());
                    plugin.setLongName(jenkinsPlugin.longName());
                    plugin.setShortName(jenkinsPlugin.shortName());
                    plugin.setVersion(jenkinsPlugin.version());
                    plugin.setUrl(jenkinsPlugin.url());
                    plugin.setRequiredCoreVersion(jenkinsPlugin.requiredCoreVersion());
                    plugin.setHasUpdate(jenkinsPlugin.hasUpdate());
                    return plugin;
                }) //
                .collect(Collectors.toList());
        return resultPlugins;
    }


    @Override
    public void installPlugin(String pluginShortName, String version) {
        String plugin = String.format("%s@%s", pluginShortName, version);
        PluginManagerApi pluginManagerApi = jenkinsClient.api().pluginManagerApi();
        RequestStatus requestStatus = pluginManagerApi.installNecessaryPlugins(plugin);
        Boolean installFlag = requestStatus.value();
        if (!installFlag.booleanValue()) {
            String message = requestStatus.errors()//
                    .stream() //
                    .map(error -> {
                        return error.message();
                    }) //
                    .collect(Collectors.joining("\n"));
            throw new RuntimeException(String.format("安装插件:[%s]失败,失败原因:[%s]", plugin, message));
        }
    }

    @Override
    public List<PluginSearchResultInfo> searchPlugin(String pluginName, Integer maxRows) {
        List<PluginSearchResultInfo> resultInfoList = new ArrayList<>(0);
        if (null == pluginName) {
            pluginName = "";
        }
        if (null == maxRows) {
            maxRows = 50;
        }

        Crumb crumb = crumbIssuerService.crumb();
        String authValue = jenkinsClient.authValue();
        String sessionIdCookie = crumb.sessionIdCookie();
        String value = crumb.value();
        String path = String.format(SEARCH_PLUGIN_PATH, pluginName.trim(), maxRows, System.nanoTime());
        String apiUrl = jenkinsClient.endPoint() + path;

        if (logger.isDebugEnabled()) {
            logger.debug("开始发起请求[GET {}] \n Cookie:[{}] \n Jenkins-Crumb:[{}]", apiUrl, sessionIdCookie.split(";")[0], value);
        }

        // 请求体
        HttpResponse<PluginSearchResult> response = Unirest.get(apiUrl) //
                .header("Accept", "*/*") //
                .header("Authorization", "Basic " + authValue) //
                .header("Jenkins-Crumb", value) //
                .header("Cookie", sessionIdCookie.split(";")[0]) //
                .asObject(PluginSearchResult.class);
        if (response.getStatus() == 200) {
            resultInfoList.addAll(response.getBody().getData());
        }
        return resultInfoList;
    }

    class PluginSearchResult {
        private String status;
        private List<PluginSearchResultInfo> data;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public List<PluginSearchResultInfo> getData() {
            return data;
        }

        public void setData(List<PluginSearchResultInfo> data) {
            this.data = data;
        }
    }
}
