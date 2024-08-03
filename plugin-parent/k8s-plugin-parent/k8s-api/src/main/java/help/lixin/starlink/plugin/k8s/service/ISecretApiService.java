package help.lixin.starlink.plugin.k8s.service;

import java.util.List;

import io.fabric8.kubernetes.api.model.Secret;
import io.fabric8.kubernetes.api.model.SecretList;
import io.fabric8.kubernetes.api.model.StatusDetails;

public interface ISecretApiService {

    Secret createSecret(Secret secret);

    Secret updateSecret(Secret secret);

    List<StatusDetails> deleteSecret(String nameSpace, String secretName);

    List<StatusDetails> deleteNameSpaceAllSecret(String nameSpace);

    SecretList querySecrets(String nameSpace);

    Secret querySecret(String nameSpace, String secretName);

}