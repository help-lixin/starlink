package help.lixin.starlink.plugin.k8s.service.impl;

import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
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
import help.lixin.starlink.plugin.k8s.mq.provider.K8sPodsEventPublish;
import help.lixin.starlink.plugin.k8s.service.IK8sServiceService;
import io.fabric8.kubernetes.api.model.Service;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/16 上午11:16
 * @Description
 */
public class K8sServiceService extends K8sBaseService implements IK8sServiceService {

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean deleteService(Long id, String userName) {
        KubernetesApp dbKubernetesApp = deleteKubernetesApp(id, userName);

        String instanceCode = dbKubernetesApp.getInstanceCode();

        ServiceApiService instance = k8sServiceFactory.getInstance(instanceCode, ServiceApiService.class);

        KubernetesNameSpace kubernetesNameSpace =
            kubernetesNameSpaceMapper.selectById(dbKubernetesApp.getNameSpaceId());
        return instance.deleteService(kubernetesNameSpace.getName(), dbKubernetesApp.getName());
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean saveService(K8sAppDTO k8sAppDTO) {
        Boolean result = saveKubernetesApp(k8sAppDTO);

        Long id = k8sAppDTO.getId();
        ServiceApiService instance =
            k8sServiceFactory.getInstance(k8sAppDTO.getInstanceCode(), ServiceApiService.class);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Service service = objectMapper.readValue(k8sAppDTO.getJsonBody(), Service.class);
            if (id == null) {
                instance.createService(service);
            } else {
                instance.updateService(service);
            }
            return result;
        } catch (Exception e) {
            throw new ServiceException("调用ServiceAPI出现异常，请联系管理员:" + e.getMessage());
        }
    }

    @Override
    public String queryById(Long id) {
        KubernetesApp kubernetesApp = kubernetesAppMapper.queryById(id);
        ServiceApiService instance =
            k8sServiceFactory.getInstance(kubernetesApp.getInstanceCode(), ServiceApiService.class);
        KubernetesNameSpace kubernetesNameSpace = kubernetesNameSpaceMapper.selectById(kubernetesApp.getNameSpaceId());
        Service service = instance.queryService(kubernetesNameSpace.getName(), kubernetesApp.getName());

        if (service == null) {
            throw new ServiceException("该service数据不存在");
        }

        try {
            ConvertUtils convertUtils = new ConvertUtils();
            service.getMetadata().setManagedFields(null);
            service.setStatus(null);
            return convertUtils.objectToYAML(service);
        } catch (JsonProcessingException e) {
            throw new ServiceException("service数据转换为YAML过程发生异常：" + e.getMessage());
        }
    }

    public K8sServiceService(AbstractServiceFactory k8sServiceFactory, QueryTemplate queryTemplate,
        KubernetesNameSpaceMapper kubernetesNameSpaceMapper, KubernetesAppMapper kubernetesAppMapper,
        K8sPodsEventPublish k8sPodsEventPublish) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.queryTemplate = queryTemplate;
        this.kubernetesNameSpaceMapper = kubernetesNameSpaceMapper;
        this.kubernetesAppMapper = kubernetesAppMapper;
        this.k8sPodsEventPublish = k8sPodsEventPublish;
    }
}
