package help.lixin.starlink.plugin.k8s.service.impl;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesAppMapper;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesNameSpaceMapper;
import help.lixin.starlink.plugin.k8s.mq.provider.K8sPodsEventPublish;
import help.lixin.starlink.plugin.k8s.service.IK8sNodeService;
import help.lixin.starlink.plugin.k8s.service.INodeApiService;

/**
 * @Author: 伍岳林
 * @Date: 2024/3/31 下午6:10
 * @Description
 */
public class K8sNodeService extends K8sBaseService implements IK8sNodeService {

    @Override
    public void stop(String instanceCode) {
        INodeApiService instance = k8sServiceFactory.getInstance(instanceCode, INodeApiService.class);
    }

    public K8sNodeService(AbstractServiceFactory k8sServiceFactory, QueryTemplate queryTemplate,
        KubernetesNameSpaceMapper kubernetesNameSpaceMapper, KubernetesAppMapper kubernetesAppMapper,
        K8sPodsEventPublish k8sPodsEventPublish) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.queryTemplate = queryTemplate;
        this.kubernetesNameSpaceMapper = kubernetesNameSpaceMapper;
        this.kubernetesAppMapper = kubernetesAppMapper;
        this.k8sPodsEventPublish = k8sPodsEventPublish;
    }
}
