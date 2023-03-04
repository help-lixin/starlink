package help.lixin.k8s.action.api;

import com.fasterxml.jackson.databind.*;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.apps.*;
import io.fabric8.kubernetes.client.dsl.*;
import io.fabric8.kubernetes.client.utils.Serialization;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeploymentTest extends BaseTest {

    @Test
    public void testGetDeploy() {
        AppsAPIGroupDSL apps = client.apps();
        RollableScalableResource<Deployment> nginxDeploy = apps.deployments().inNamespace("default").withName("nginx-deployment");
        Assert.assertEquals("nginx-deployment", nginxDeploy.get().getMetadata().getName());
    }

    /**
     * 弹性伸缩
     */
    @Test
    public void testEditReplicas() {
        AppsAPIGroupDSL apps = client.apps();
        Deployment deployment = apps.deployments().inNamespace("default").withName("nginx-deployment").scale(2, true);
        Assert.assertNotNull(deployment);
    }


    /**
     * 创建部署
     */
    @Test
    public void testCreateNginxDeployment() {
        Map<String, String> podLabels = new HashMap<>();
        podLabels.put("app", "nginx-deploy");
//

        AppsAPIGroupDSL apps = client.apps();
        NonNamespaceOperation<Deployment, DeploymentList, RollableScalableResource<Deployment>> deploymentService = apps.deployments().inNamespace("default");
        Deployment deployment = deploymentService.create(new DeploymentBuilder()
                //
                .withApiVersion("apps/v1")
                //
                .withKind("Deployment")
                //
                .withMetadata(new ObjectMetaBuilder()
                        //
                        .withName("nginx-deployment")
                        //
                        .withNamespace("default")
                        //
                        .build()).withSpec(new DeploymentSpecBuilder()
                        //
                        .withSelector(new LabelSelectorBuilder().withMatchLabels(podLabels).build())
                        //
                        .withTemplate(new PodTemplateSpecBuilder()
                                //
                                .withMetadata(new ObjectMetaBuilder().withLabels(podLabels).build())
                                //
                                .withSpec(new PodSpecBuilder()
                                        // .withImagePullSecrets(new LocalObjectReferenceBuilder().build())
                                        .withContainers(new ContainerBuilder().withImage("docker.io/library/nginx:latest").withName("nginx").build()).build()).build())
                        //
                        .build())
                //
                .build());
        Assert.assertNotNull(deployment);
    }


    @Test
    public void testYamlToDeployment() throws Exception {
        String appName = "nginx-deployment";
        AppsAPIGroupDSL apps = client.apps();
        ItemWritableOperation<Deployment> deploymentService = apps.deployments().inNamespace("default").dryRun(true);
        StringWriter writer = new StringWriter();
        IOUtils.copy(new FileInputStream(new File("/tmp/deploy.yml")), writer, "UTF-8");
        String yamlDeploy = writer.toString();
        Deployment deployment = Serialization.yamlMapper().readValue(yamlDeploy, Deployment.class);
        Assert.assertEquals(deployment.getMetadata().getName(), appName);
    }

    @Test
    public void testDeployToYAML() throws Exception {
        Map<String, String> podLabels = new HashMap<>();
        podLabels.put("app", "nginx-deploy");

        Deployment deployment = new DeploymentBuilder()
                //
                .withApiVersion("apps/v1")
                //
                .withKind("Deployment")
                //
                .withMetadata(new ObjectMetaBuilder()
                        //
                        .withName("nginx-deployment")
                        //
                        .withNamespace("default")
                        //
                        .build())
                //
                .withSpec(new DeploymentSpecBuilder()
                        //
                        .withSelector(new LabelSelectorBuilder().withMatchLabels(podLabels).build())
                        //
                        .withTemplate(new PodTemplateSpecBuilder()
                                //
                                .withMetadata(new ObjectMetaBuilder().withLabels(podLabels).build())
                                //
                                .withSpec(new PodSpecBuilder()
                                        //
                                        .withContainers(new ContainerBuilder()
                                                //
                                                .withImage("docker.io/library/nginx:latest")
                                                //
                                                .withName("nginx")
                                                //
                                                .build())
                                        //
                                        .build())
                                //
                                .build())
                        //
                        .build())
                //
                .build();

        // 把提交上来的内容对象,转换成YAML
        ObjectMapper mapper = Serialization.yamlMapper();
        String s = mapper.writeValueAsString(deployment);
        String path = "/tmp/deploy.yml";
        IOUtils.write(s, new FileOutputStream(new File(path)));
        // 通过YAML文件处理.
//        Deployment newDeploy = apps.deployments().inNamespace("default").load(path).create();
//        Assert.assertNotNull(newDeploy);
    }

    @Test
    public void testDeleteNginxDeployment() {
        AppsAPIGroupDSL apps = client.apps();
        RollableScalableResource<Deployment> nginxDeploy = apps.deployments().inNamespace("default").withName("nginx-deployment");
        if (null != nginxDeploy) {
            Deployment deployment = nginxDeploy.get();
            Assert.assertEquals("nginx-deployment", deployment.getMetadata().getName());
            apps.deployments().delete(deployment);
        }
    }


    @Test
    public void testQueryDeploy() {
        AppsAPIGroupDSL apps = client.apps();
        Map<String, String> labels = new HashMap<>();
        labels.put("app", "nginx-deploy");

        MixedOperation<Deployment, DeploymentList, RollableScalableResource<Deployment>> deployments = apps.deployments();
        List<StatusDetails> statusDetails = deployments.delete(new DeploymentBuilder().withApiVersion("apps/v1").withKind("Deployment").withMetadata(new ObjectMetaBuilder()
                //
                .withName("nginx-deployment")
                //
                .withNamespace("default")
                //
                .withLabels(labels)
                //
                .build()).build());
        Assert.assertNotNull(statusDetails);
    }
}