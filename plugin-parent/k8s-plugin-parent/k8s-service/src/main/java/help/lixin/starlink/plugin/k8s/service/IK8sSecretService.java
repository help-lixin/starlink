package help.lixin.starlink.plugin.k8s.service;

import java.util.List;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.dto.secret.SecretOptionDTO;
import help.lixin.starlink.plugin.k8s.dto.secret.SecretSaveDTO;

public interface IK8sSecretService {

    List<SecretOptionDTO> querySecrets(String instanceCode, String nameSpace);

    SecretOptionDTO querySecret(K8sAppDTO baseInfoDTO);

    Boolean saveSecret(SecretSaveDTO secretSaveDTO);

    Boolean deleteSecret(K8sAppDTO baseInfoDTO);

    Boolean deleteNameSpaceAllSecret(String instanceCode, String nameSpace);

}
