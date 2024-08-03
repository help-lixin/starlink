package help.lixin.starlink.plugin.jenkins.api.service.impl;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.crumb.Crumb;
import help.lixin.starlink.plugin.jenkins.api.model.JenkinsManageToolsModule;
import help.lixin.starlink.plugin.jenkins.api.service.ICrumbIssuerService;
import help.lixin.starlink.plugin.jenkins.api.service.IManageToolsConfigureService;
import kong.unirest.HttpResponse;
import kong.unirest.RequestBodyEntity;
import kong.unirest.Unirest;

public class ManageToolsConfigureService implements IManageToolsConfigureService {
    private ICrumbIssuerService crumbIssuerService;
    private JenkinsClient jenkinsClient;

    private static final String SAVE_CONFIGURE_PATH = "/manage/configureTools/configure";

    private static final String CHECK_NAME_PATH = "/manage/descriptorByName/%s/checkName";
    private static final String CHECK_HOME_PATH = "/manage/descriptorByName/%s/checkHome";

    private static final String CHECK_SUCCESS_BODY = "<div/>";

    public ManageToolsConfigureService(ICrumbIssuerService crumbIssuerService, JenkinsClient jenkinsClient) {
        this.crumbIssuerService = crumbIssuerService;
        this.jenkinsClient = jenkinsClient;
    }

    @Override
    public boolean configure(String jsonBody) {
        Crumb crumb = crumbIssuerService.crumb();
        String authValue = jenkinsClient.authValue();
        String sessionIdCookie = crumb.sessionIdCookie();
        String value = crumb.value();

        String apiUrl = jenkinsClient.endPoint() + SAVE_CONFIGURE_PATH;
        // 请求体
        RequestBodyEntity request = Unirest.post(apiUrl) //
                .header("Content-Type", "application/x-www-form-urlencoded") //
                .header("Accept", "text/html,application/xhtml+xml,application/xml") //
                .header("Authorization", "Basic " + authValue) //
                .header("Jenkins-Crumb", value) //
                .header("Cookie", sessionIdCookie.split(";")[0]) //
                .body("json="+jsonBody);
        HttpResponse<String> response = request.asString();
        return response.getStatus() == 302;
    }

    @Override
    public void checkName(JenkinsManageToolsModule module, String name) {
        if (null == module || null == name) return;
        Crumb crumb = crumbIssuerService.crumb();
        String authValue = jenkinsClient.authValue();
        String sessionIdCookie = crumb.sessionIdCookie();
        String value = crumb.value();
        String path = String.format(CHECK_NAME_PATH, module.getDescription());
        String apiUrl = jenkinsClient.endPoint() + path;
        // 请求体
        HttpResponse<String> response = Unirest.post(apiUrl) //
                .header("Content-Type", "application/x-www-form-urlencoded") //
                .header("Accept", "text/html,application/xhtml+xml,application/xml") //
                .header("Authorization", "Basic " + authValue) //
                .header("Jenkins-Crumb", value) //
                .header("Cookie", sessionIdCookie.split(";")[0]) //
                .field("value", name.trim()) //
                .asString();
        // 从多次测试效果来看,好像永远都是200
        if (response.getStatus() == 200) {
            String body = response.getBody();
            if (!(CHECK_SUCCESS_BODY.equals(body))) {
                throw new RuntimeException(body);
            }
        } else {
            throw new RuntimeException(response.getBody());
        }
    }

    @Override
    public void checkHome(JenkinsManageToolsModule module, String home) {
        if (null == module || null == home) return;
        Crumb crumb = crumbIssuerService.crumb();
        String authValue = jenkinsClient.authValue();
        String sessionIdCookie = crumb.sessionIdCookie();
        String value = crumb.value();
        String path = String.format(CHECK_HOME_PATH, module.getDescription());
        String apiUrl = jenkinsClient.endPoint() + path;
        // 请求体
        HttpResponse<String> response = Unirest.post(apiUrl) //
                .header("Content-Type", "application/x-www-form-urlencoded") //
                .header("Accept", "text/html,application/xhtml+xml,application/xml") //
                .header("Authorization", "Basic " + authValue) //
                .header("Jenkins-Crumb", value) //
                .header("Cookie", sessionIdCookie.split(";")[0]) //
                .field("value", home.trim()) //
                .asString();
        if (response.getStatus() == 200) {
            String body = response.getBody();
            if (!(CHECK_SUCCESS_BODY.equals(body))) {
                throw new RuntimeException(body);
            }
        } else {
            throw new RuntimeException(response.getBody());
        }
    }
}
