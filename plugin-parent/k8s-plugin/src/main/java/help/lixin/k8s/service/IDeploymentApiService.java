package help.lixin.k8s.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import help.lixin.k8s.constants.Constant;
import io.fabric8.kubernetes.api.model.apps.Deployment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface IDeploymentApiService {
    Deployment apply(Deployment deployment);

    Deployment apply(String yamlContent);

    Deployment apply(File yamlFile) throws IOException;

    String deploymentConvertYAML(Deployment deployment) throws JsonProcessingException;

    Deployment yamlConvertDeployment(String yaml) throws JsonProcessingException;

    Deployment get(String namespace, String deployName);

    default Deployment get(String deployName) {
        return get(Constant.DEFAULT_NAMESPACE, deployName);
    }

    Boolean delete(String namespace, String deployName);

    default Boolean delete(String deployName) {
        return delete(Constant.DEFAULT_NAMESPACE, deployName);
    }

    Deployment scale(String namespace, String deployName, int scale, boolean wait);

    default Deployment scale(String deployName, int scale, boolean wait) {
        return scale(Constant.DEFAULT_NAMESPACE, deployName, scale, Boolean.TRUE);
    }
}
