package help.lixin.starlink.plugin.k8s.service.impl;

import java.util.List;

import help.lixin.starlink.plugin.k8s.service.ISecretApiService;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.KubernetesClient;
import io.fabric8.kubernetes.client.dsl.MixedOperation;
import io.fabric8.kubernetes.client.dsl.Resource;

/**
 * @Author: 伍岳林
 * @Date: 2024/4/1 上午11:08
 * @Description
 */
public class SecretApiService implements ISecretApiService {
    private KubernetesClient client;

    public SecretApiService(KubernetesClient client) {
        this.client = client;
    }

    @Override
    public Secret createSecret(Secret secret) {
        MixedOperation<Secret, SecretList, Resource<Secret>> secrets = client.secrets();
        ObjectMeta metadata = secret.getMetadata();
        return secrets.inNamespace(metadata.getNamespace()).resource(secret).create();
    }

    @Override
    public Secret updateSecret(Secret secret) {
        ObjectMeta metadata = secret.getMetadata();
        return client.secrets().inNamespace(metadata.getNamespace()).withName(metadata.getName())
                .edit(s -> new SecretBuilder(secret).build());
    }

    @Override
    public List<StatusDetails> deleteSecret(String nameSpace, String secretName) {
        MixedOperation<Secret, SecretList, Resource<Secret>> secrets = client.secrets();
        return secrets.inNamespace(nameSpace).withName(secretName).delete();
    }

    @Override
    public List<StatusDetails> deleteNameSpaceAllSecret(String nameSpace) {
        MixedOperation<Secret, SecretList, Resource<Secret>> secrets = client.secrets();
        return secrets.inNamespace(nameSpace).delete();
    }

    @Override
    public SecretList querySecrets(String nameSpace) {
        MixedOperation<Secret, SecretList, Resource<Secret>> secrets = client.secrets();
        return secrets.inNamespace(nameSpace).list();
    }

    @Override
    public Secret querySecret(String nameSpace, String secretName) {
        MixedOperation<Secret, SecretList, Resource<Secret>> secrets = client.secrets();
        return secrets.inNamespace(nameSpace).withName(secretName).get();
    }


}
