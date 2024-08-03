package help.lixin.starlink.plugin.k8s.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;

import help.lixin.exception.ServiceException;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.dto.ContainerDTO;
import help.lixin.starlink.plugin.k8s.dto.pod.PodContainerGroupDTO;
import help.lixin.starlink.plugin.k8s.dto.pod.PodLogDTO;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesAppMapper;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesNameSpaceMapper;
import help.lixin.starlink.plugin.k8s.mq.provider.K8sPodsEventPublish;
import help.lixin.starlink.plugin.k8s.service.IK8sPodService;
import help.lixin.starlink.plugin.k8s.utils.ConvertUtils;
import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.ContainerStatus;
import io.fabric8.kubernetes.api.model.Pod;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/16 上午11:15
 * @Description
 */
public class K8sPodService extends K8sBaseService implements IK8sPodService {

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean deletePod(Long id, String userName) {
        KubernetesApp dbKubernetesApp = deleteKubernetesApp(id, userName);

        String instanceCode = dbKubernetesApp.getInstanceCode();
        PodApiService instance = k8sServiceFactory.getInstance(instanceCode, PodApiService.class);

        KubernetesNameSpace kubernetesNameSpace =
            kubernetesNameSpaceMapper.selectById(dbKubernetesApp.getNameSpaceId());
        return instance.deletePod(kubernetesNameSpace.getName(), dbKubernetesApp.getName());
    }

    @Override
    public String queryById(Long id) {
        KubernetesApp kubernetesApp = kubernetesAppMapper.queryById(id);
        PodApiService instance = k8sServiceFactory.getInstance(kubernetesApp.getInstanceCode(), PodApiService.class);
        KubernetesNameSpace kubernetesNameSpace = kubernetesNameSpaceMapper.selectById(kubernetesApp.getNameSpaceId());
        Pod pod = instance.queryPod(kubernetesNameSpace.getName(), kubernetesApp.getName());

        if (pod == null) {
            throw new ServiceException("该Pod数据不存在");
        }

        try {
            ConvertUtils convertUtils = new ConvertUtils();
            pod.getMetadata().setManagedFields(null);
            pod.setStatus(null);
            return convertUtils.objectToYAML(pod);
        } catch (JsonProcessingException e) {
            throw new ServiceException("pod数据转换为YAML过程发生异常：" + e.getMessage());
        }
    }

    @Override
    public String queryLog(PodLogDTO podLogDTO) {
        PodApiService instance = k8sServiceFactory.getInstance(podLogDTO.getInstanceCode(), PodApiService.class);
        return instance.queryLog(podLogDTO.getNameSpace(), podLogDTO.getPodName(), podLogDTO.getContainerName());
    }

    @Override
    public List<ContainerDTO> queryContainerGroup(PodContainerGroupDTO podContainerGroupDTO) {
        PodApiService instance =
            k8sServiceFactory.getInstance(podContainerGroupDTO.getInstanceCode(), PodApiService.class);
        Pod pod = instance.queryPod(podContainerGroupDTO.getNameSpace(), podContainerGroupDTO.getPodName());

        List<ContainerDTO> containersResultList = new ArrayList<>();
        List<Container> containers = pod.getSpec().getContainers();
        for (int i = 0; i < containers.size(); i++) {
            Container container = containers.get(i);
            ContainerStatus containerStatus = pod.getStatus().getContainerStatuses().get(i);

            ContainerDTO containerDTO = new ContainerDTO();
            containerDTO.setContainerName(container.getName());
            containerDTO.setImageName(container.getImage());
            containerDTO.setStatus(containerStatus.getStarted());
            containerDTO.setRestartCount(containerStatus.getRestartCount());
            containersResultList.add(containerDTO);
        }
        return containersResultList;
    }

    public K8sPodService(AbstractServiceFactory k8sServiceFactory, QueryTemplate queryTemplate,
        KubernetesNameSpaceMapper kubernetesNameSpaceMapper, KubernetesAppMapper kubernetesAppMapper,
        K8sPodsEventPublish k8sPodsEventPublish) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.queryTemplate = queryTemplate;
        this.kubernetesNameSpaceMapper = kubernetesNameSpaceMapper;
        this.kubernetesAppMapper = kubernetesAppMapper;
        this.k8sPodsEventPublish = k8sPodsEventPublish;
    }

}
