package help.lixin.starlink.plugin.jenkins.api.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.crumb.Crumb;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.jenkins.api.model.CredentialEnum;
import help.lixin.starlink.plugin.jenkins.api.service.ICrumbIssuerService;
import help.lixin.starlink.plugin.jenkins.api.service.IKeyManagerService;
import kong.unirest.HttpResponse;
import kong.unirest.RequestBodyEntity;
import kong.unirest.Unirest;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/29 3:46 下午
 * @Description
 */
public class KeyManagerService implements IKeyManagerService {

    private ICrumbIssuerService crumbIssuerService;
    private JenkinsClient jenkinsClient;

    private final String CREATE_CREDENTIALS_PATH = "/manage/credentials/store/system/domain/_/createCredentials";
    private final String CREDENTIALS_PATH = "/manage/credentials/store/system/domain/_/credential/";

    @Override
    public Boolean createCredentials(String credentialJson, String credentialsId, CredentialEnum credentialEnum) {
        try {
            String jsonBody = "json={\"credentials\" :" + credentialJson + "}";
            connection(jsonBody, CREATE_CREDENTIALS_PATH);
        } catch (Exception e) {
            if (checkCredentialsId(credentialsId, credentialEnum)) {
                return true;
            }
            throw new ServiceException("创建失败");
        }
        return false;
    }

    @Override
    public Boolean deleteCredentials(String credentialsId, CredentialEnum credentialEnum) {
        try {
            connection(null, CREDENTIALS_PATH + credentialsId + "/doDelete");
        } catch (Exception e) {
            return checkCredentialsId(credentialsId, credentialEnum);
        }
        return false;
    }

    @Override
    public Boolean updateCredentials(String credentialJson, String credentialsId) {

        String jsonBody = "json=" + credentialJson;
        try {
            connection(jsonBody, CREDENTIALS_PATH + credentialsId + "/updateSubmit");
        } catch (Exception e) {
            if (e.getMessage().contains("500")) {
                throw new ServiceException("更新失败");
            }
        }
        return false;
    }

    /**
     * @Param credentialsId :凭证key
     * @Param path : 查询地址
     * @Author: 伍岳林
     * @Date: 2023/7/3 3:00 下午
     * @Return: java.lang.Boolean ture:存在该id
     * @Description
     */
    @Override
    public Boolean checkCredentialsId(String credentialsId, CredentialEnum credentialEnum) {
        String success = "<div/>";
        try {
            String response;
            response = connection("value=" + credentialsId, "POST", credentialEnum.getDescription());
            return !response.equals(success);
        } catch (IOException e) {
            throw new ServiceException("请求发生异常::" + e.getMessage());
        }

    }

    @Override
    public Boolean checkGitCredentialsId(String credentialsId, String path, CredentialEnum credentialEnum,
        String jobId) {
        String success = "<div/>";
        try {
            String response;
            response = connection("value=" + credentialsId + "&path=" + path, "POST",
                "/job/" + jobId + credentialEnum.getDescription());
            return !response.equals(success);
        } catch (IOException e) {
            throw new ServiceException("请求发生异常::" + e.getMessage());
        }

    }

    private String connection(String requestBody, String method, String path) throws IOException {
        try {
            Crumb crumb = crumbIssuerService.crumb();
            String authValue = jenkinsClient.authValue();
            String sessionIdCookie = crumb.sessionIdCookie();
            String value = crumb.value();

            // Jenkins实例的URL
            String jenkinsUrl = jenkinsClient.endPoint();
            // 请求的API终端点
            String apiUrl = jenkinsUrl + path;

            // 创建URL对象
            URL url = new URL(apiUrl);

            // 打开连接
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);

            // 设置请求头
            // connection.setRequestProperty("Content-Accept", "*/*");
            connection.setRequestProperty("Jenkins-Crumb", value);
            connection.setRequestProperty("Cookie", sessionIdCookie.split(";")[0]);
            connection.setRequestProperty("Authorization", "Basic " + authValue);

            if (StringUtils.isNotBlank(requestBody)) {
                byte[] requestBodyBytes = requestBody.getBytes(StandardCharsets.UTF_8);
                // 设置请求体长度
                connection.setRequestProperty("Content-Length", String.valueOf(requestBodyBytes.length));
                // 发送请求体数据
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(requestBodyBytes);
            }

            InputStream inputStream = connection.getInputStream();
            return IOUtils.toString(inputStream, "UTF-8");
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    private String connection(String requestBody, String path) {
        Crumb crumb = crumbIssuerService.crumb();
        String authValue = jenkinsClient.authValue();
        String sessionIdCookie = crumb.sessionIdCookie();
        String value = crumb.value();

        String apiUrl = jenkinsClient.endPoint() + path;
        // 请求体
        RequestBodyEntity request = Unirest.post(apiUrl) //
            .header("Content-Type", "application/x-www-form-urlencoded") //
            .header("Accept", "text/html,application/xhtml+xml,application/xml") //
            .header("Authorization", "Basic " + authValue) //
            .header("Jenkins-Crumb", value) //
            .header("Cookie", sessionIdCookie.split(";")[0]) //
            .body(requestBody);
        HttpResponse<String> response = request.asString();
        return response.getBody();
    }

    public KeyManagerService(ICrumbIssuerService crumbIssuerService, JenkinsClient jenkinsClient) {
        this.crumbIssuerService = crumbIssuerService;
        this.jenkinsClient = jenkinsClient;
    }

}
