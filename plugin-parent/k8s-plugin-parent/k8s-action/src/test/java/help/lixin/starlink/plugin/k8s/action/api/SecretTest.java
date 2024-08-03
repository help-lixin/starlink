package help.lixin.starlink.plugin.k8s.action.api;

import io.fabric8.kubernetes.api.model.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class SecretTest extends BaseTest {

    @Test
    public void testSaveSecret() throws Exception {
        Map<String, String> data = new HashMap();
        data.put(".dockerconfigjson", "eyJhdXRocyI6eyJyZWdpc3RyeS5saXhpbi5oZWxwIjp7InVzZXJuYW1lIjoiYWRtaW4iLCJwYXNzd29yZCI6IkxpQDg4ODg4ODg4In19fQ==");

        Secret build = new SecretBuilder()
                //
                .withApiVersion("v1")
                //
                .withKind("Secret")
                //
                .withType("kubernetes.io/dockerconfigjson")
                //
                .withMetadata(new ObjectMetaBuilder()
                        .withName("harbor")
                        .withNamespace("default")
                        .build())
                //
                .addToData(data)
                //
                .build();
        Secret secret = client.secrets().inNamespace("default").create(build);
        Assert.assertNotNull(secret);
    }

    @Test
    public void testListSecret() {
        // lixin-macbook:~ lixin$ kubectl get secrets
        //NAME                  TYPE                                  DATA   AGE
        //default               kubernetes.io/dockerconfigjson        1      22s
        //default-token-9bhlj   kubernetes.io/service-account-token   3      22h
        //loginharbor           kubernetes.io/dockerconfigjson        1      21h
        SecretList list = client.secrets().inNamespace("default").list(new ListOptionsBuilder().build());
        Assert.assertNotNull(list);
    }

    @Test
    public void testDeleteSecret() {
        client.secrets().inNamespace("default").withName("harbor").delete();
    }
}