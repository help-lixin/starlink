package help.lixin.starlink.plugin.jenkins.api.service.impl;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.crumb.Crumb;
import help.lixin.starlink.plugin.jenkins.api.service.ICrumbIssuerService;
import help.lixin.starlink.plugin.jenkins.api.service.IUpdateSiteService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateSiteService implements IUpdateSiteService {

    private Logger logger = LoggerFactory.getLogger(UpdateSiteService.class);

    private ICrumbIssuerService crumbIssuerService;
    private JenkinsClient jenkinsClient;

    private static final String CHECK_UPDATE_SITE_URL = "/manage/pluginManager/checkUpdateSiteUrl";
    private static final String UPDATE_SITE_URL = "/manage/pluginManager/siteConfigure";

    public UpdateSiteService(ICrumbIssuerService crumbIssuerService, //
                             JenkinsClient jenkinsClient) {
        this.crumbIssuerService = crumbIssuerService;
        this.jenkinsClient = jenkinsClient;
    }

    @Override
    public boolean checkUpdateSite(String url) {
        Crumb crumb = crumbIssuerService.crumb();
        String authValue = jenkinsClient.authValue();
        String sessionIdCookie = crumb.sessionIdCookie();
        String value = crumb.value();

        String apiUrl = jenkinsClient.endPoint() + CHECK_UPDATE_SITE_URL;
        // 请求体
        HttpResponse<String> response = Unirest.post(apiUrl) //
                .header("Content-Type", "application/x-www-form-urlencoded") //
                .header("Accept", "text/html,application/xhtml+xml,application/xml") //
                .header("Authorization", "Basic " + authValue) //
                .header("Jenkins-Crumb", value) //
                .header("Cookie", sessionIdCookie.split(";")[0]) //
                .field("value", url).asString();
        return response.getStatus() == 200;
    }

    @Override
    public void updateSite(String url) {
        if (null == url) return;
        Crumb crumb = crumbIssuerService.crumb();
        String authValue = jenkinsClient.authValue();
        String sessionIdCookie = crumb.sessionIdCookie();
        String value = crumb.value();
        String apiUrl = jenkinsClient.endPoint() + UPDATE_SITE_URL;
        // 请求体
        HttpResponse<String> response = Unirest.post(apiUrl) //
                .header("Content-Type", "application/x-www-form-urlencoded") //
                .header("Accept", "text/html,application/xhtml+xml,application/xml") //
                .header("Authorization", "Basic " + authValue) //
                .header("Jenkins-Crumb", value) //
                .header("Cookie", sessionIdCookie.split(";")[0]) //
                .field("site", url) // 配置站点
                .asString();
        if (response.getStatus() != 302) {
            String msg = String.format("发送[更新站点请求]失败,失败原因:\n[%s]\n", response.getBody());
            throw new RuntimeException(msg);
        }
    }
}
