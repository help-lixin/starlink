package help.lixin.starlink.plugin.k8s.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.fabric8.kubernetes.api.model.Status;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentList;

public interface IDeploymentApiService {

    Deployment createDeployment(Deployment deployment);

    Deployment updateDeployment(Deployment deployment);

    Deployment apply(Deployment deployment);

    Deployment apply(String yamlContent);

    Deployment apply(File yamlFile) throws IOException;

    String deploymentConvertYAML(Deployment deployment) throws JsonProcessingException;

    Deployment yamlConvertDeployment(String yaml) throws JsonProcessingException;

    Deployment queryDeployment(String namespace, String deployName);

    DeploymentList queryDeployments(String namespace);

    Boolean deleteDeployment(String namespace, String deployName);

    Deployment scale(String namespace, String deployName, int scale, boolean wait);

    List<Deployment> showHistory(String namespace, String deployName);

    Status rollBack(String namespace, String deployName, Long version);
}
