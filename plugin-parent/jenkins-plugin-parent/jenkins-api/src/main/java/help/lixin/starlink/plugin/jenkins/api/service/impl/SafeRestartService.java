package help.lixin.starlink.plugin.jenkins.api.service.impl;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.crumb.Crumb;
import help.lixin.starlink.plugin.jenkins.api.service.ICrumbIssuerService;
import help.lixin.starlink.plugin.jenkins.api.service.ISafeRestartService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SafeRestartService implements ISafeRestartService {
    private Logger logger = LoggerFactory.getLogger(SafeRestartService.class);

    private ICrumbIssuerService crumbIssuerService;
    private JenkinsClient jenkinsClient;

    private static final String SAFE_RESTART_PATH = "/manage/pluginManager/updates/safeRestart";

    public SafeRestartService(ICrumbIssuerService crumbIssuerService, //
                              JenkinsClient jenkinsClient) {
        this.crumbIssuerService = crumbIssuerService;
        this.jenkinsClient = jenkinsClient;
    }

    @Override
    public void safeRestart() {
        Crumb crumb = crumbIssuerService.crumb();
        String authValue = jenkinsClient.authValue();
        String sessionIdCookie = crumb.sessionIdCookie();
        String value = crumb.value();
        String apiUrl = jenkinsClient.endPoint() + SAFE_RESTART_PATH;

        if (logger.isDebugEnabled()) {
            logger.debug("开始发起[安全重启]请求[GET {}] \n Cookie:[{}] \n Jenkins-Crumb:[{}]", apiUrl, sessionIdCookie.split(";")[0], value);
        }

        HttpResponse<String> response = Unirest.post(apiUrl) //
                .header("Accept", "*/*") //
                .header("Authorization", "Basic " + authValue) //
                .header("Jenkins-Crumb", value) //
                .header("Cookie", sessionIdCookie.split(";")[0]) //
                .asString();
        if (response.getStatus() == 302) {
            if (logger.isDebugEnabled()) {
                logger.debug("正在[安全重启]中....");
            }
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("[安全重启]失败,失败详细信息如下:\n[{}]\n", response.getBody());
            }
        }
    }
}
