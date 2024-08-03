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
import help.lixin.starlink.plugin.k8s.job.IServiceSyncService;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesAppMapper;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesNameSpaceMapper;
import help.lixin.starlink.plugin.k8s.service.impl.NameSpaceApiService;
import help.lixin.starlink.plugin.k8s.service.impl.ServiceApiService;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.ServiceList;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/21 下午4:30
 * @Description
 */
public class ServiceSyncService implements IServiceSyncService {

    private Logger logger = LoggerFactory.getLogger(ServiceSyncService.class);
    private KubernetesAppMapper kubernetesAppMapper;
    private KubernetesNameSpaceMapper kubernetesNameSpaceMapper;
    private final AbstractServiceFactory k8sServiceFactory;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void syncService() {
        // 获取插件列表
        Set<String> contextNames = k8sServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            logger.info("插件列表为空");
            return;
        }

        // 遍历插件列表
        contextNames.forEach(instanceCode -> {
            logger.info("instanceCode：{} 开始拉取K8S Service信息", instanceCode);
            ServiceApiService instance = k8sServiceFactory.getInstance(instanceCode, ServiceApiService.class);
            NameSpaceApiService nameSpaceApiService =
                k8sServiceFactory.getInstance(instanceCode, NameSpaceApiService.class);

            List<Namespace> namespaceList = nameSpaceApiService.queryNameSpaces().getItems();

            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("is_del", 0);
            queryWrapper.eq("kind", "Service");
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

                ServiceList serviceList = instance.queryServices(namespace.getMetadata().getName());
                List<Service> serviceListItems = serviceList.getItems();
                if (CollectionUtils.isEmpty(serviceListItems)) {
                    continue;
                }

                for (Service service : serviceListItems) {
                    KubernetesApp kubernetesApp = new KubernetesApp();
                    String name = service.getMetadata().getNamespace();

                    if (nameAndIdMap.get(name) == null) {
                        continue;
                    }
                    kubernetesApp.setName(service.getMetadata().getName());
                    kubernetesApp.setInstanceCode(instanceCode);
                    kubernetesApp.setNameSpaceId(nameAndIdMap.get(name));
                    kubernetesApp.setKind("Service");
                    kubernetesAppMapper.insertSelective(kubernetesApp);
                }
            }
            logger.info("Service拉取成功！");

        });
    }

    public ServiceSyncService(KubernetesAppMapper kubernetesAppMapper,
        KubernetesNameSpaceMapper kubernetesNameSpaceMapper, AbstractServiceFactory k8sServiceFactory) {
        this.kubernetesAppMapper = kubernetesAppMapper;
        this.kubernetesNameSpaceMapper = kubernetesNameSpaceMapper;
        this.k8sServiceFactory = k8sServiceFactory;
    }
}
