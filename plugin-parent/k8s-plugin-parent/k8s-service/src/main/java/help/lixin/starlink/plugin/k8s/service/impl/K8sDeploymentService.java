package help.lixin.starlink.plugin.k8s.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.dto.ContainerDTO;
import help.lixin.starlink.plugin.k8s.dto.PodGroupResponseDTO;
import help.lixin.starlink.plugin.k8s.dto.PodGroupResultDTO;
import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.dto.deployment.DeploymentPodGroupDTO;
import help.lixin.starlink.plugin.k8s.dto.deployment.DeploymentUpdateReplicasDTO;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesAppMapper;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesNameSpaceMapper;
import help.lixin.starlink.plugin.k8s.mq.provider.K8sPodsEventPublish;
import help.lixin.starlink.plugin.k8s.service.IK8sDeploymentService;
import help.lixin.starlink.plugin.k8s.utils.ConvertUtils;
import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.apps.Deployment;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/16 上午11:15
 * @Description
 */
public class K8sDeploymentService extends K8sBaseService implements IK8sDeploymentService {

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean deleteDeployment(Long id, String userName) {
        KubernetesApp dbKubernetesApp = deleteKubernetesApp(id, userName);

        String instanceCode = dbKubernetesApp.getInstanceCode();
        DeploymentApiService instance = k8sServiceFactory.getInstance(instanceCode, DeploymentApiService.class);

        KubernetesNameSpace kubernetesNameSpace =
            kubernetesNameSpaceMapper.selectById(dbKubernetesApp.getNameSpaceId());
        return instance.deleteDeployment(kubernetesNameSpace.getName(), dbKubernetesApp.getName());
    }

    @Override
    public String queryById(Long id) {
        KubernetesApp kubernetesApp = kubernetesAppMapper.queryById(id);
        DeploymentApiService instance =
            k8sServiceFactory.getInstance(kubernetesApp.getInstanceCode(), DeploymentApiService.class);
        KubernetesNameSpace kubernetesNameSpace = kubernetesNameSpaceMapper.selectById(kubernetesApp.getNameSpaceId());
        Deployment deployment = instance.queryDeployment(kubernetesNameSpace.getName(), kubernetesApp.getName());

        if (deployment == null) {
            throw new ServiceException("该deployment数据不存在");
        }

        try {
            ConvertUtils convertUtils = new ConvertUtils();
            deployment.getMetadata().setManagedFields(null);
            deployment.setStatus(null);
            return convertUtils.objectToYAML(deployment);
        } catch (JsonProcessingException e) {
            throw new ServiceException("deployment数据转换为YAML过程发生异常：" + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean saveDeployment(K8sAppDTO k8sAppDTO) {
        Boolean result = saveKubernetesApp(k8sAppDTO);

        Long id = k8sAppDTO.getId();
        DeploymentApiService instance =
            k8sServiceFactory.getInstance(k8sAppDTO.getInstanceCode(), DeploymentApiService.class);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Deployment deployment = objectMapper.readValue(k8sAppDTO.getJsonBody(), Deployment.class);
            if (id == null) {
                instance.createDeployment(deployment);
            } else {
                instance.updateDeployment(deployment);
            }
            return result;
        } catch (Exception e) {
            throw new ServiceException("调用deploymentAPI出现异常，请联系管理员:" + e.getMessage());
        }
    }

    @Override
    public Boolean updateReplicas(DeploymentUpdateReplicasDTO deploymentUpdateReplicasDTO) {
        DeploymentApiService depInstance =
            k8sServiceFactory.getInstance(deploymentUpdateReplicasDTO.getInstanceCode(), DeploymentApiService.class);

        Deployment deployment = depInstance.queryDeployment(deploymentUpdateReplicasDTO.getNameSpace(),
            deploymentUpdateReplicasDTO.getDeployName());
        if (deployment == null) {
            throw new ServiceException("该deployment不存在");
        }

        deployment.getSpec().setReplicas(deploymentUpdateReplicasDTO.getReplicas());
        depInstance.updateDeployment(deployment);

        return true;
    }

    @Override
    public PodGroupResultDTO queryDeploymentPodGroup(DeploymentPodGroupDTO deploymentPodGroupDTO) {
        DeploymentApiService depInstance =
            k8sServiceFactory.getInstance(deploymentPodGroupDTO.getInstanceCode(), DeploymentApiService.class);

        Deployment deployment = depInstance.queryDeployment(deploymentPodGroupDTO.getNameSpace(),
            deploymentPodGroupDTO.getDeploymentName());

        Map<String, String> matchLabels = deployment.getSpec().getSelector().getMatchLabels();

        PodApiService podInstance =
            k8sServiceFactory.getInstance(deploymentPodGroupDTO.getInstanceCode(), PodApiService.class);
        List<Pod> pods = podInstance.queryPodGroup(deploymentPodGroupDTO.getNameSpace(), matchLabels);

        List<PodGroupResponseDTO> resultList = new ArrayList<>();

        for (Pod pod : pods) {
            List<Container> containers = pod.getSpec().getContainers();
            PodGroupResponseDTO podGroupResponseDTO = new PodGroupResponseDTO();

            podGroupResponseDTO.setPodName(pod.getMetadata().getName());
            podGroupResponseDTO.setStatus(pod.getStatus().getPhase());
            podGroupResponseDTO.setNameSpace(pod.getMetadata().getNamespace());
            podGroupResponseDTO.setContainerAmount(containers.size());
            podGroupResponseDTO.setNodeName(pod.getSpec().getNodeName());
            podGroupResponseDTO.setIpAddress(pod.getStatus().getPodIP());
            List<ContainerDTO> containersResult = new ArrayList<>();

            for (int i = 0; i < containers.size(); i++) {
                Container container = containers.get(i);

                ContainerDTO containerDTO = new ContainerDTO();
                containerDTO.setContainerName(container.getName());
                containerDTO.setImageName(container.getImage());
                containerDTO.setStatus(pod.getStatus().getContainerStatuses().get(i).getStarted());
                containerDTO.setRestartCount(pod.getStatus().getContainerStatuses().get(i).getRestartCount());
                containersResult.add(containerDTO);
            }

            podGroupResponseDTO.setContainers(containersResult);
            resultList.add(podGroupResponseDTO);
        }

        PodGroupResultDTO podGroupResultDTO = new PodGroupResultDTO();
        podGroupResultDTO.setPods(resultList);
        podGroupResultDTO.setReplicas(deployment.getSpec().getReplicas());
        podGroupResultDTO.setCurReplicas(deployment.getStatus().getReadyReplicas());

        return podGroupResultDTO;
    }

    public K8sDeploymentService(AbstractServiceFactory k8sServiceFactory, QueryTemplate queryTemplate,
        KubernetesNameSpaceMapper kubernetesNameSpaceMapper, KubernetesAppMapper kubernetesAppMapper,
        K8sPodsEventPublish k8sPodsEventPublish) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.queryTemplate = queryTemplate;
        this.kubernetesNameSpaceMapper = kubernetesNameSpaceMapper;
        this.kubernetesAppMapper = kubernetesAppMapper;
        this.k8sPodsEventPublish = k8sPodsEventPublish;
    }

}
