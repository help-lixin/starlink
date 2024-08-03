package help.lixin.starlink.plugin.k8s.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.dto.secret.SecretOptionDTO;
import help.lixin.starlink.plugin.k8s.dto.secret.SecretSaveDTO;
import help.lixin.starlink.plugin.k8s.service.IK8sSecretService;
import io.fabric8.kubernetes.api.model.Secret;
import io.fabric8.kubernetes.api.model.SecretList;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/16 上午11:16
 * @Description
 */
public class K8sSecretService implements IK8sSecretService {

    private final AbstractServiceFactory k8sServiceFactory;
    private Logger logger = LoggerFactory.getLogger(K8sSecretService.class);

    @Override
    public List<SecretOptionDTO> querySecrets(String instanceCode, String nameSpace) {
        List<SecretOptionDTO> resultList = new ArrayList<>();

        SecretApiService secretApiService = k8sServiceFactory.getInstance(instanceCode, SecretApiService.class);
        SecretList secretList = secretApiService.querySecrets(nameSpace);
        if (secretList != null) {
            secretList.getItems().forEach(item -> {
                String secretName = item.getMetadata().getName();
                SecretOptionDTO secretOptionDTO = new SecretOptionDTO();
                secretOptionDTO.setLabel(secretName);
                secretOptionDTO.setValue(secretName);
                resultList.add(secretOptionDTO);
            });

            return resultList;
        } else {
            throw new ServiceException("密文不存在，请确认后再次查询");
        }
    }

    @Override
    public SecretOptionDTO querySecret(K8sAppDTO baseInfoDTO) {
        SecretApiService secretApiService =
            k8sServiceFactory.getInstance(baseInfoDTO.getInstanceCode(), SecretApiService.class);
        Secret secret = secretApiService.querySecret(baseInfoDTO.getNamespace(), baseInfoDTO.getName());
        if (secret != null) {
            SecretOptionDTO secretOptionDTO = new SecretOptionDTO();
            secretOptionDTO.setLabel(secret.getFullResourceName());
            secretOptionDTO.setValue(secret.getFullResourceName());
            return secretOptionDTO;
        } else {
            return null;
        }

    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean saveSecret(SecretSaveDTO secretSaveDTO) {
        SecretApiService secretApiService =
            k8sServiceFactory.getInstance(secretSaveDTO.getInstanceCode(), SecretApiService.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Secret secret = null;
        try {

            secret = objectMapper.readValue(secretSaveDTO.getJsonBody(), Secret.class);
        } catch (JsonProcessingException e) {
            logger.error("k8s创建凭证转换出现异常：" + e.getMessage());
            throw new ServiceException(e.getMessage());
        }

        SecretOptionDTO querySecret = querySecret(secretSaveDTO);

        if (querySecret == null) {
            secretApiService.createSecret(secret);
        } else {
            secretApiService.updateSecret(secret);
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean deleteSecret(K8sAppDTO baseInfoDTO) {
        SecretApiService secretApiService =
            k8sServiceFactory.getInstance(baseInfoDTO.getInstanceCode(), SecretApiService.class);
        secretApiService.deleteSecret(baseInfoDTO.getNamespace(), baseInfoDTO.getName());
        return true;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean deleteNameSpaceAllSecret(String instanceCode, String nameSpace) {
        SecretApiService secretApiService = k8sServiceFactory.getInstance(instanceCode, SecretApiService.class);
        secretApiService.deleteNameSpaceAllSecret(nameSpace);
        return true;
    }

    public K8sSecretService(AbstractServiceFactory k8sServiceFactory) {
        this.k8sServiceFactory = k8sServiceFactory;
    }
}
