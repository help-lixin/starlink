package help.lixin.k8s.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.k8s.constants.Constant;
import help.lixin.k8s.service.IDeploymentApiService;
import io.fabric8.kubernetes.api.model.StatusDetails;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.AppsAPIGroupDSL;
import io.fabric8.kubernetes.client.dsl.RollableScalableResource;
import io.fabric8.kubernetes.client.utils.Serialization;
import org.apache.commons.io.IOUtils;
import org.springframework.util.Assert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class DeploymentApiService implements IDeploymentApiService {

    private KubernetesClient client;

    private AppsAPIGroupDSL apps;

    public DeploymentApiService(KubernetesClient client) {
        this.client = client;
        this.apps = client.apps();
    }

    @Override
    public Deployment apply(Deployment deployment) {
        Assert.notNull(deployment.getMetadata().getName(), "deployment.metadata.name is require");
        // 配置默认的命名空间
        if (null == deployment.getMetadata().getNamespace()) {
            deployment.getMetadata().setNamespace(Constant.DEFAULT_NAMESPACE);
        }
        try {
            // YAML描述文件
            String ymlDesc = deploymentConvertYAML(deployment);
            ByteArrayInputStream ymlStream = new ByteArrayInputStream(ymlDesc.getBytes(StandardCharsets.UTF_8));
            Deployment newDeploy = apps.deployments().load(ymlStream).createOrReplace();
            return newDeploy;
        } catch (Exception e) {
            System.out.println(e);
            // TODO lixin
        }
        return null;
    }

    @Override
    public Deployment apply(String yamlContent) {
        Deployment deployment = null;
        try {
            deployment = yamlConvertDeployment(yamlContent);
            Assert.notNull(deployment.getMetadata().getName(), "deployment.metadata.name is require");
            // 配置默认的命名空间
            if (null == deployment.getMetadata().getNamespace()) {
                deployment.getMetadata().setNamespace(Constant.DEFAULT_NAMESPACE);
            }
            ByteArrayInputStream ymlStream = new ByteArrayInputStream(yamlContent.getBytes(StandardCharsets.UTF_8));
            apps.deployments().load(ymlStream);
        } catch (Exception e) {
            // TODO lixin
        }
        return null;
    }

    @Override
    public Deployment apply(File yamlFile) throws IOException {
        if (yamlFile.exists()) {
            StringWriter writer = new StringWriter();
            IOUtils.copy(new FileInputStream(yamlFile), writer, StandardCharsets.UTF_8);
            return this.apply(writer.toString());
        }
        return null;
    }

    @Override
    public String deploymentConvertYAML(Deployment deployment) throws JsonProcessingException {
        ObjectMapper mapper = Serialization.yamlMapper();
        String yaml = mapper.writeValueAsString(deployment);
        return yaml;
    }

    @Override
    public Deployment yamlConvertDeployment(String yaml) throws JsonProcessingException {
        Deployment deployment = Serialization.yamlMapper().readValue(yaml, Deployment.class);
        return deployment;
    }

    @Override
    public Deployment get(String namespace, String deployName) {
        namespace = checkAndReturnNamespace(namespace, deployName);
        Deployment deployment = apps.deployments().inNamespace(namespace).withName(deployName).get();
        return deployment;
    }


    @Override
    public Boolean delete(String namespace, String deployName) {
        namespace = checkAndReturnNamespace(namespace, deployName);
        List<StatusDetails> statusDetails = apps.deployments().inNamespace(namespace).withName(deployName).delete();
        return null != statusDetails && statusDetails.size() > 0;
    }

    @Override
    public Deployment scale(String namespace, String deployName, int scale, boolean wait) {
        namespace = checkAndReturnNamespace(namespace, deployName);
        AppsAPIGroupDSL apps = client.apps();
        Deployment deployment = apps.deployments().inNamespace(namespace).withName(deployName).scale(scale, wait);
        return deployment;
    }

    protected String checkAndReturnNamespace(String namespace, String deployName) {
        namespace = null == namespace ? Constant.DEFAULT_NAMESPACE : namespace;
        Assert.notNull(deployName, "deployName is require");
        return namespace;
    }
}
