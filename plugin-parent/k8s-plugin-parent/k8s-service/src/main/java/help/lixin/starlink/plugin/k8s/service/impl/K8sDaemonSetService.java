package help.lixin.starlink.plugin.k8s.service.impl;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.mq.provider.K8sPodsEventPublish;
import help.lixin.starlink.plugin.k8s.utils.ConvertUtils;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesAppMapper;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesNameSpaceMapper;
import help.lixin.starlink.plugin.k8s.service.IK8sDaemonSetService;
import io.fabric8.kubernetes.api.model.apps.DaemonSet;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/16 上午11:14
 * @Description
 */
public class K8sDaemonSetService extends K8sBaseService implements IK8sDaemonSetService {

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean deleteDaemonSet(Long id, String userName) {
        KubernetesApp dbKubernetesApp = deleteKubernetesApp(id, userName);

        String instanceCode = dbKubernetesApp.getInstanceCode();

        DaemonSetApiService instance = k8sServiceFactory.getInstance(instanceCode, DaemonSetApiService.class);

        KubernetesNameSpace kubernetesNameSpace =
            kubernetesNameSpaceMapper.selectById(dbKubernetesApp.getNameSpaceId());
        return instance.deleteDaemonSet(kubernetesNameSpace.getName(), dbKubernetesApp.getName());
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean saveDaemonSet(K8sAppDTO k8sAppDTO) {
        Boolean result = saveKubernetesApp(k8sAppDTO);

        Long id = k8sAppDTO.getId();
        DaemonSetApiService instance =
            k8sServiceFactory.getInstance(k8sAppDTO.getInstanceCode(), DaemonSetApiService.class);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            DaemonSet daemonSet = objectMapper.readValue(k8sAppDTO.getJsonBody(), DaemonSet.class);
            if (id == null) {
                instance.createDaemonSet(daemonSet);
            } else {
                instance.updateDaemonSet(daemonSet);
            }
            return result;
        } catch (Exception e) {
            throw new ServiceException("调用DaemonSetAPI出现异常，请联系管理员:" + e.getMessage());
        }
    }

    @Override
    public String queryById(Long id) {
        KubernetesApp kubernetesApp = kubernetesAppMapper.queryById(id);
        DaemonSetApiService instance =
            k8sServiceFactory.getInstance(kubernetesApp.getInstanceCode(), DaemonSetApiService.class);
        KubernetesNameSpace kubernetesNameSpace = kubernetesNameSpaceMapper.selectById(kubernetesApp.getNameSpaceId());
        DaemonSet daemonSet = instance.queryDaemonSet(kubernetesNameSpace.getName(), kubernetesApp.getName());

        if (daemonSet == null) {
            throw new ServiceException("该daemonSet数据不存在");
        }

        try {
            ConvertUtils convertUtils = new ConvertUtils();
            daemonSet.getMetadata().setManagedFields(null);
            daemonSet.setStatus(null);
            return convertUtils.objectToYAML(daemonSet);
        } catch (JsonProcessingException e) {
            throw new ServiceException("daemonSet数据转换为YAML过程发生异常：" + e.getMessage());
        }
    }

    public K8sDaemonSetService(AbstractServiceFactory k8sServiceFactory, QueryTemplate queryTemplate,
        KubernetesNameSpaceMapper kubernetesNameSpaceMapper, KubernetesAppMapper kubernetesAppMapper,
        K8sPodsEventPublish k8sPodsEventPublish) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.queryTemplate = queryTemplate;
        this.kubernetesNameSpaceMapper = kubernetesNameSpaceMapper;
        this.kubernetesAppMapper = kubernetesAppMapper;
        this.k8sPodsEventPublish = k8sPodsEventPublish;
    }

}
