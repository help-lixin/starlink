package help.lixin.starlink.plugin.k8s.job.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.job.IDeploymentSyncService;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesAppMapper;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesNameSpaceMapper;
import help.lixin.starlink.plugin.k8s.service.impl.DeploymentApiService;
import help.lixin.starlink.plugin.k8s.service.impl.NameSpaceApiService;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentList;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/21 下午4:30
 * @Description
 */
public class DeploymentSyncService implements IDeploymentSyncService {

    private Logger logger = LoggerFactory.getLogger(DeploymentSyncService.class);
    private KubernetesAppMapper kubernetesAppMapper;
    private KubernetesNameSpaceMapper kubernetesNameSpaceMapper;
    private final AbstractServiceFactory k8sServiceFactory;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void syncDeployment() {
        // 获取插件列表
        Set<String> contextNames = k8sServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            logger.info("插件列表为空");
            return;
        }

        // 遍历插件列表
        contextNames.forEach(instanceCode -> {
            logger.info("instanceCode：{} 开始拉取K8S Deployment信息", instanceCode);
            DeploymentApiService instance = k8sServiceFactory.getInstance(instanceCode, DeploymentApiService.class);
            NameSpaceApiService nameSpaceApiService =
                k8sServiceFactory.getInstance(instanceCode, NameSpaceApiService.class);

            List<Namespace> namespaceList = nameSpaceApiService.queryNameSpaces().getItems();

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("is_del", 0);
            queryWrapper.eq("kind", "Deployment");
            kubernetesAppMapper.delete(queryWrapper);

            List<String> nameSpaceList =
                namespaceList.stream().map(v -> v.getMetadata().getName()).collect(Collectors.toList());
            List<KubernetesNameSpace> kubernetesNameSpaces = kubernetesNameSpaceMapper.queryInNameSpace(nameSpaceList);
            if (CollectionUtils.isEmpty(kubernetesNameSpaces)) {
                return;
            }
            Map<String, Long> nameAndIdMap =
                kubernetesNameSpaces.stream().collect(Collectors.toMap(KubernetesNameSpace::getName, v -> v.getId()));

            for (Namespace namespace : namespaceList) {

                DeploymentList deploymentList = instance.queryDeployments(namespace.getMetadata().getName());
                if (deploymentList.getItems().size() == 0) {
                    continue;
                }

                for (Deployment deployment : deploymentList.getItems()) {
                    KubernetesApp kubernetesApp = new KubernetesApp();
                    String name = deployment.getMetadata().getNamespace();

                    if (nameAndIdMap.get(name) == null) {
                        continue;
                    }
                    kubernetesApp.setName(deployment.getMetadata().getName());
                    kubernetesApp.setInstanceCode(instanceCode);
                    kubernetesApp.setNameSpaceId(nameAndIdMap.get(name));
                    kubernetesApp.setKind("Deployment");
                    kubernetesAppMapper.insertSelective(kubernetesApp);
                }
            }
            logger.info("Deployment拉取成功！");
        });
    }

    public DeploymentSyncService(KubernetesAppMapper kubernetesAppMapper,
        KubernetesNameSpaceMapper kubernetesNameSpaceMapper, AbstractServiceFactory k8sServiceFactory) {
        this.kubernetesAppMapper = kubernetesAppMapper;
        this.kubernetesNameSpaceMapper = kubernetesNameSpaceMapper;
        this.k8sServiceFactory = k8sServiceFactory;
    }
}
