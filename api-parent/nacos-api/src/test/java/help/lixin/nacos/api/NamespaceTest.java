package help.lixin.nacos.api;

import kong.unirest.Unirest;
import org.junit.Assert;
import org.junit.Test;

public class NamespaceTest {

    private static final String URL = "http://localhost:8848";
    private static final String ADD_NAMESPACE = "/nacos/v1/console/namespaces";

    private static final String UPDATE_NAMESPACE = "/nacos/v1/console/namespaces";

    private final String customNamespaceId = "2da932a86c094af0b3a293eda58fa074";

    @Test
    public void testAddNamespace() {
        String request = Unirest.post(URL + ADD_NAMESPACE)
                // 命名空间ID
                .queryString("customNamespaceId", customNamespaceId)
                // 命名空间名
                .queryString("namespaceName", "dev")
                // 命名空间描述
                .queryString("namespaceDesc", "开发环境")
                //
                .asString()
                //
                .getBody();
        Assert.assertEquals("true", request);
    }


    @Test
    public void testUpdateNamespace() {
        String request = Unirest.put(URL + UPDATE_NAMESPACE)
                // 命名空间ID
                .queryString("namespace", customNamespaceId)
                // 命名空间名
                .queryString("namespaceShowName", "_dev")
                // 命名空间描述
                .queryString("namespaceDesc", "dev开发环境")
                //
                .asString()
                //
                .getBody();
        Assert.assertEquals("true", request);
    }


    @Test
    public void testDeleteNamespace() {
        String request = Unirest.delete(URL + UPDATE_NAMESPACE)
                // 命名空间ID
                .queryString("namespaceId", customNamespaceId)
                //
                .asString()
                //
                .getBody();
        Assert.assertEquals("true", request);
    }
}
