package help.lixin.nacos.api;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.Assert;
import org.junit.Test;

public class ConfigTest {

    private static final String URL = "http://localhost:8848";

    private static final String PUBLIC = "0c4bfa7d-3478-4a3f-bef9-6e52e88d8ce2";

    private static final String DEPLOY_CONFIG = "/nacos/v1/cs/configs";

    @Test
    public void deployConfig() {
        String tenant = PUBLIC;
        String dataId = "test-provider";
        String group = "DEFAULT_GROUP";
        String content = "spring.application.name=test-provider-3";
        String type = "properties";
        HttpResponse<String> httpResponse = Unirest.post(URL + DEPLOY_CONFIG)
                // 命名空间id
                .queryString("tenant", tenant)
                .queryString("dataId", dataId)
                .queryString("group", group)
                .queryString("content", content)
                .queryString("type", type)
                .asString();
        int status = httpResponse.getStatus();
        Assert.assertEquals(status, 200);
        Assert.assertEquals(httpResponse.getBody(), "true");
    }

    @Test
    public void getConfig() {
        String tenant = PUBLIC;
        String dataId = "test-provider";
        String group = "DEFAULT_GROUP";
        String content = "spring.application.name=test-provider-3";
        String type = "properties";
        HttpResponse<String> httpResponse = Unirest.get(URL + DEPLOY_CONFIG)
                // 命名空间id
                .queryString("tenant", tenant)
                .queryString("dataId", dataId)
                .queryString("group", group)
                .asString();
        int status = httpResponse.getStatus();
        Assert.assertEquals(status, 200);
        Assert.assertNotNull(httpResponse.getBody());
    }
}
