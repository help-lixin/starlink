package help.lixin.starlink.plugin.k8s.job.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import help.lixin.starlink.plugin.k8s.job.INodeSyncService;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesAppMapper;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesNameSpaceMapper;
import help.lixin.starlink.plugin.k8s.service.impl.NodeApiService;
import io.fabric8.kubernetes.api.model.Node;
import io.fabric8.kubernetes.api.model.NodeList;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/21 下午4:30
 * @Description
 */
public class NodeSyncService implements INodeSyncService {

    private Logger logger = LoggerFactory.getLogger(NodeSyncService.class);
    private KubernetesAppMapper kubernetesAppMapper;
    private KubernetesNameSpaceMapper kubernetesNameSpaceMapper;
    private final AbstractServiceFactory k8sServiceFactory;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void syncNode() {
        // 获取插件列表
        Set<String> contextNames = k8sServiceFactory.getPluginNamedContextFactory().getContextNames();
        if (CollectionUtils.isEmpty(contextNames)) {
            logger.info("插件列表为空");
            return;
        }

        // 遍历插件列表
        contextNames.forEach(instanceCode -> {
            logger.info("instanceCode：{} 开始拉取K8S节点信息", instanceCode);
            NodeApiService instance = k8sServiceFactory.getInstance(instanceCode, NodeApiService.class);
            NodeList nodeList = instance.queryNods();
            List<Node> items = nodeList.getItems();
            if (CollectionUtils.isEmpty(items)) {
                return;
            }

            QueryWrapper<KubernetesApp> queryWrapper = new QueryWrapper();
            queryWrapper.eq("kind", "Node");
            kubernetesAppMapper.delete(queryWrapper);

            for (Node node : items) {
                KubernetesApp kubernetesApp = new KubernetesApp();

                kubernetesApp.setName(node.getMetadata().getName());
                kubernetesApp.setInstanceCode(instanceCode);
                kubernetesApp.setNameSpaceId(0l);
                kubernetesApp.setKind("Node");
                kubernetesAppMapper.insertSelective(kubernetesApp);
            }
            logger.info("节点拉取成功！");
        });
    }

    public NodeSyncService(KubernetesAppMapper kubernetesAppMapper, KubernetesNameSpaceMapper kubernetesNameSpaceMapper,
        AbstractServiceFactory k8sServiceFactory) {
        this.kubernetesAppMapper = kubernetesAppMapper;
        this.kubernetesNameSpaceMapper = kubernetesNameSpaceMapper;
        this.k8sServiceFactory = k8sServiceFactory;
    }
}
