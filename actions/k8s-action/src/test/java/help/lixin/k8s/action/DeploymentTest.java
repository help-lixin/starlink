package help.lixin.k8s.action;

import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.api.model.apps.*;
import io.fabric8.kubernetes.client.dsl.AppsAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.NonNamespaceOperation;
import io.fabric8.kubernetes.client.dsl.RollableScalableResource;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class DeploymentTest extends BaseTest {

    @Test
    public void testGetDeploy() {
//        AppsAPIGroupDSL apps = client.apps();
//        RollableScalableResource<Deployment, DoneableDeployment> nginxDeploy = apps.deployments().inNamespace("default").withName("nginx-deployment");
//        Assert.assertEquals("nginx-deployment", nginxDeploy.get().getMetadata().getName());
    }

    /**
     * 弹性伸缩
     */
    @Test
    public void testEditReplicas() {
//        AppsAPIGroupDSL apps = client.apps();
//        Deployment aDefault = apps.deployments().inNamespace("default")
//                //
//                .withName("nginx-deployment")
//                //
//                .edit()
//                //
//                .editSpec()
//                //
//                .withReplicas(2)
//                //
//                .endSpec()
//                //
//                .done();
    }


    /**
     * 创建部署
     */
    @Test
    public void testCreateNginxDeployment() {
//        Map<String, String> podLabels = new HashMap<>();
//        podLabels.put("app", "nginx-deploy");
//
//        AppsAPIGroupDSL apps = client.apps();
//        NonNamespaceOperation<Deployment, DeploymentList, DoneableDeployment, RollableScalableResource<Deployment, DoneableDeployment>> deploymentService = apps.deployments().inNamespace("default");
//        Deployment deployment = deploymentService.create(new DeploymentBuilder()
//                .withApiVersion("apps/v1")
//                .withKind("Deployment")
//                .withMetadata(new ObjectMetaBuilder()
//                        .withName("nginx-deployment")
//                        .withNamespace("default")
//                        //
//                        .build())
//                .withSpec(new DeploymentSpecBuilder()
//                        //
//                        .withSelector(new LabelSelectorBuilder().withMatchLabels(podLabels).build())
//                        //
//                        .withTemplate(new PodTemplateSpecBuilder()
//                                //
//                                .withMetadata(new ObjectMetaBuilder().withLabels(podLabels).build())
//                                //
//                                .withSpec(new PodSpecBuilder()
//                                        // .withImagePullSecrets(new LocalObjectReferenceBuilder().build())
//                                        .withContainers(new ContainerBuilder()
//                                                .withImage("docker.io/library/nginx:latest")
//                                                .withName("nginx")
//                                                .build())
//                                        .build())
//                                .build())
//                        //
//                        .build())
//                //
//                .build());
//        Assert.assertNotNull(deployment);
    }

    @Test
    public void testDeleteNginxDeployment() {
//        AppsAPIGroupDSL apps = client.apps();
//        RollableScalableResource<Deployment, DoneableDeployment> nginxDeploy = apps.deployments().inNamespace("default").withName("nginx-deployment");
//        if (null != nginxDeploy) {
//            Deployment deployment = nginxDeploy.get();
//            Assert.assertEquals("nginx-deployment", deployment.getMetadata().getName());
//            apps.deployments().delete(deployment);
//        }
    }


    @Test
    public void testQueryDeploy() {
//        AppsAPIGroupDSL apps = client.apps();
//        Map<String, String> labels = new HashMap<>();
//        labels.put("app", "nginx-deploy");
//
//        MixedOperation<Deployment, DeploymentList, DoneableDeployment, RollableScalableResource<Deployment, DoneableDeployment>> deployments = apps.deployments();
//        Boolean isSuccess = deployments.delete(new DeploymentBuilder()
//                .withApiVersion("apps/v1")
//                .withKind("Deployment")
//                .withMetadata(new ObjectMetaBuilder()
//                        //
//                        .withName("nginx-deployment")
//                        //
//                        .withClusterName("default")
//                        //
//                        // .withLabels(labels)
//                        //
//                        .build())
//                .build());
//        Assert.assertEquals(true, isSuccess);
    }
}
