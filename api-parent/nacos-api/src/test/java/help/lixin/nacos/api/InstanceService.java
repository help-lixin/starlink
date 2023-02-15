package help.lixin.nacos.api;

import kong.unirest.HttpResponse;
import kong.unirest.JsonObjectMapper;
import kong.unirest.Unirest;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class InstanceService {

    private static final String URL = "http://localhost:8848";

    private static final String REGISTER_INSTANCE = "/nacos/v1/ns/instance";

    private static final String DESTORY_INSTANCE = "/nacos/v1/ns/instance";

    private static final String BEAT = "/nacos/v1/ns/instance/beat";

    private static final String INSTANCE_LIST = "/nacos/v1/ns/instance/list";

    private static final String INSTANCE = "/nacos/v1/ns/instance";

    private static final String SERVICE_LIST = "/nacos/v1/ns/service/list";

    @Test
    public void testRegisterInstance() {
        // 元数据定义
        Map<String, String> metadata = new HashMap<>();
        metadata.put("version", "1.0");
        JsonObjectMapper objectMapper = new JsonObjectMapper();
        String metadataString = objectMapper.writeValue(metadata);

        HttpResponse<String> stringHttpResponse = Unirest.post(URL + REGISTER_INSTANCE)
                // 服务名
                .queryString("serviceName", "test-provider")
                //
                .queryString("ip", "127.0.0.1")
                //
                .queryString("port", 9999)
                //
                // .queryString("namespaceId", "")
                // 权重
                .queryString("weight", 1)
                // 是否上线
                .queryString("enabled", Boolean.TRUE)
                // 是否健康
                .queryString("healthy", Boolean.TRUE)
                // 扩展信息
                .queryString("metadata", metadataString)
                // 集群名称
                .queryString("clusterName", "DEFAULT")
                // 分组名称
                .queryString("groupName", "DEFAULT_GROUP")
                // 是否临时实例
                .queryString("ephemeral", Boolean.TRUE)
                //
                .asString();
        int status = stringHttpResponse.getStatus();
        Assert.assertEquals(200, status);
        String body = stringHttpResponse.getBody();
        Assert.assertEquals("ok", body);

        Thread t = new Thread() {
            public void run() {
                while (true) {
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (Exception e) {
                    }
                    testBeat();
                }
            }
        };

        t.start();
        try {
            t.join();
        } catch (Exception e) {
        }

    }

    @Test
    public void testBeat() {

        HttpResponse<String> httpResponse = Unirest.put(URL + BEAT)
                // 	服务名
                .queryString("serviceName", "test-provider")
                // 服务实例IP
                .queryString("ip", "127.0.0.1")
                //
                .queryString("port", 9999)
                //
                // .queryString("namespaceId", "")
                // 分组名
                .queryString("groupName", "DEFAULT_GROUP")
                // 是否临时实例
                .queryString("ephemeral", Boolean.TRUE)
                //
                .queryString("beat", "")
                //
                .asString();
        int status = httpResponse.getStatus();
        String body = httpResponse.getBody();
        // beat: {"clientBeatInterval":5000,"code":10200,"lightBeatEnabled":true}
        System.out.println("beat: " + body);
    }


    @Test
    public void testDestoryInstance() {

        HttpResponse<String> httpResponse = Unirest.delete(URL + DESTORY_INSTANCE)
                // 服务名
                .queryString("serviceName", "test-provider")
                // 分组名
                .queryString("groupName", "DEFAULT_GROUP")
                //
                .queryString("ip", "127.0.0.1")
                //
                .queryString("port", 9999)
                // 集群名称
                .queryString("clusterName", "DEFAULT")
                //
                // .queryString("namespaceId", "")
                // 是否临时实例
                .queryString("ephemeral", Boolean.TRUE)
                //
                .asString();

        int status = httpResponse.getStatus();
        Assert.assertEquals(200, status);
        String body = httpResponse.getBody();
        Assert.assertEquals("ok", body);
    }

    @Test
    @Deprecated
    public void testServiceList() {
        HttpResponse<String> httpResponse = Unirest.get(URL + SERVICE_LIST)
                // 当前页码
                .queryString("pageNo", 0)
                // 分页大小
                .queryString("pageSize", 10)
                // 分组名
                .queryString("groupName", "DEFAULT_GROUP")
                //
                // .queryString("namespaceId","")
                //
                .asString();
        Assert.assertEquals(200, httpResponse.getStatus());
        // {"doms":["test-provider"],"count":1}
        System.out.println(httpResponse.getBody());
    }


    @Test
    public void testInstanceList() {
        HttpResponse<String> httpResponse = Unirest.get(URL + INSTANCE_LIST)
                // 服务名
                .queryString("serviceName", "test-provider")
                // 分组名
                .queryString("groupName", "DEFAULT_GROUP")
                //
                // .queryString("namespaceId","")
                // 集群名称(多个集群用逗号分隔)
                .queryString("clusters", "DEFAULT")
                // 是否只返回健康实例
                .queryString("healthyOnly", Boolean.TRUE)
                .asString();
        Assert.assertEquals(200, httpResponse.getStatus());
        // {"name":"DEFAULT_GROUP@@test-provider","groupName":"DEFAULT_GROUP","clusters":"DEFAULT","cacheMillis":10000,"hosts":[{"instanceId":"127.0.0.1#9999#DEFAULT#DEFAULT_GROUP@@test-provider","ip":"127.0.0.1","port":9999,"weight":1.0,"healthy":true,"enabled":true,"ephemeral":true,"clusterName":"DEFAULT","serviceName":"DEFAULT_GROUP@@test-provider","metadata":{"version":"1.0"},"ipDeleteTimeout":30000,"instanceHeartBeatTimeOut":15000,"instanceHeartBeatInterval":5000}],"lastRefTime":1676435815776,"checksum":"","allIPs":false,"reachProtectionThreshold":false,"valid":true}
        System.out.println(httpResponse.getBody());
    }


    @Test
    public void testInstance() {
        HttpResponse<String> httpResponse = Unirest.get(URL + INSTANCE)
                // 服务名
                .queryString("serviceName", "test-provider")
                //
                .queryString("ip", "127.0.0.1")
                //
                .queryString("port", 9999)
                // 分组名
                .queryString("groupName", "DEFAULT_GROUP")
                // .queryString("namespaceId", "")
                // 集群名称
                .queryString("cluster", "DEFAULT")
                // 是否只返回健康实例
                .queryString("healthyOnly", Boolean.TRUE)
                // 是否临时实例
                .queryString("ephemeral", Boolean.TRUE)
                .asString();
        int status = httpResponse.getStatus();
        Assert.assertEquals(200, status);
        String body = httpResponse.getBody();
        // {"service":"DEFAULT_GROUP@@test-provider","ip":"127.0.0.1","port":9999,"clusterName":"DEFAULT","weight":1.0,"healthy":true,"instanceId":"127.0.0.1#9999#DEFAULT#DEFAULT_GROUP@@test-provider","metadata":{"version":"1.0"}}
        System.out.println(body);
    }
}
