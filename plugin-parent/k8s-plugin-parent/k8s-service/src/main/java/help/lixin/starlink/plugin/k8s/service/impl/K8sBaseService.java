package help.lixin.starlink.plugin.k8s.service.impl;

import java.util.Date;
import java.util.List;

import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.dto.base.K8sAppDTO;
import help.lixin.starlink.plugin.k8s.dto.base.KubernetesAppDTO;
import help.lixin.starlink.plugin.k8s.dto.namespace.NameSpaceOptionDTO;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesAppMapper;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesNameSpaceMapper;
import help.lixin.starlink.plugin.k8s.mq.event.K8sSyncPodEvent;
import help.lixin.starlink.plugin.k8s.mq.provider.K8sPodsEventPublish;
import help.lixin.starlink.plugin.k8s.service.IK8sBaseService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.response.PageResponse;

public abstract class K8sBaseService implements IK8sBaseService {

    protected AbstractServiceFactory k8sServiceFactory;
    protected KubernetesNameSpaceMapper kubernetesNameSpaceMapper;
    protected KubernetesAppMapper kubernetesAppMapper;
    protected QueryTemplate queryTemplate;
    protected K8sPodsEventPublish k8sPodsEventPublish;

    @Override
    public KubernetesNameSpace queryNameSpace(String instanceCode, String nameSpace) {
        return kubernetesNameSpaceMapper.queryNameSpaceByNameAndInstanceCode(instanceCode, nameSpace);
    }

    @Override
    public List<KubernetesNameSpace> queryNameSpaces(String instanceCode) {
        return kubernetesNameSpaceMapper.queryNameSpaceByInstanceCode(instanceCode);

    }

    @Override
    public List<NameSpaceOptionDTO> queryNameSpaceOption(String instanceCode) {
        return kubernetesNameSpaceMapper.queryNameSpaceOptionByInstanceCode(instanceCode);
    }

    @Override
    public PageResponse<KubernetesAppDTO> queryPageList(BasePageListDTO basePageListDTO) {
        return queryTemplate.execute(basePageListDTO, () -> {
            kubernetesAppMapper.queryPageList(basePageListDTO);
        });
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean saveKubernetesApp(K8sAppDTO k8sAppDTO) {
        KubernetesApp kubernetesApp = new KubernetesApp();
        K8sSyncPodEvent k8sSyncPodEvent = new K8sSyncPodEvent();

        kubernetesApp.setCreateBy(k8sAppDTO.getUserName());
        Long id = k8sAppDTO.getId();

        // 新增
        if (id == null) {
            List<KubernetesApp> kubernetesApps =
                kubernetesAppMapper.queryInstanceAndNameIsExist(k8sAppDTO.getInstanceCode(), k8sAppDTO.getName());
            if (!CollectionUtils.isEmpty(kubernetesApps)) {
                throw new ServiceException("此名称在该实例中已存在，请确认后重试");
            }

            kubernetesApp.setInstanceCode(k8sAppDTO.getInstanceCode());
            kubernetesApp.setKind(k8sAppDTO.getKind());
            kubernetesApp.setName(k8sAppDTO.getName());
            kubernetesApp.setNameSpaceId(k8sAppDTO.getNameSpaceId());
            kubernetesAppMapper.insertSelective(kubernetesApp);

        } else {
            // 更新
            kubernetesApp = kubernetesAppMapper.queryById(id);

            if (kubernetesApp == null) {
                throw new ServiceException("此id不存在，请确认后重试");
            }

            if (!kubernetesApp.getName().equals(k8sAppDTO.getName())
                && !kubernetesApp.getInstanceCode().equals(k8sAppDTO.getInstanceCode())
                && !kubernetesApp.getKind().equals(k8sAppDTO.getKind())
                && !kubernetesApp.getNameSpaceId().equals(k8sAppDTO.getNameSpaceId())) {
                throw new ServiceException("存在不允许变更数据，请确认后重试");
            }

            kubernetesApp.setUpdateTime(new Date());
            kubernetesAppMapper.updateById(kubernetesApp);
            k8sSyncPodEvent.setId(kubernetesApp.getId());

        }

        if (!k8sAppDTO.getKind().equals("Pod")) {
            k8sSyncPodEvent.setInstanceCode(k8sAppDTO.getInstanceCode());
            k8sSyncPodEvent.setName(k8sAppDTO.getName());
            k8sSyncPodEvent.setNameSpace(k8sAppDTO.getNamespace());
            k8sSyncPodEvent.setUserName(k8sAppDTO.getUserName());
            k8sPodsEventPublish.syncPods(k8sSyncPodEvent);
        }

        return true;

    }

    @Override
    public Boolean nameIsExist(String instanceCode, String name) {
        return kubernetesAppMapper.queryInstanceAndNameIsExist(instanceCode, name).size() > 0;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean changeStatus(Integer status, Long id, String userName) {
        KubernetesApp kubernetesApp = kubernetesAppMapper.queryById(id);
        if (kubernetesApp == null) {
            throw new ServiceException("该id不存在，请检查后重试");
        }
        kubernetesApp.setStatus(status);
        kubernetesApp.setUpdateBy(userName);
        return kubernetesAppMapper.updateById(kubernetesApp) > 0;
    }

    @Override
    public String queryById(Long id) {
        return null;
    }

    @Override
    public KubernetesApp deleteKubernetesApp(Long id, String userName) {
        KubernetesApp dbKubernetesApp = kubernetesAppMapper.queryById(id);
        if (dbKubernetesApp == null) {
            throw new ServiceException("该id不存在");
        }
        dbKubernetesApp.setIsDel(1);
        dbKubernetesApp.setUpdateBy(userName);
        kubernetesAppMapper.updateById(dbKubernetesApp);
        return dbKubernetesApp;
    }

    @Override
    public Boolean deleteKubernetesAppByName(String instanceCode, String name) {

        List<KubernetesApp> dbKubernetesApp = kubernetesAppMapper.queryPodsByInstanceAndName(instanceCode, name);
        for (KubernetesApp kubernetesApp : dbKubernetesApp) {
            kubernetesApp.setIsDel(1);
            kubernetesApp.setUpdateBy(null);
            kubernetesAppMapper.updateById(kubernetesApp);
        }
        return true;
    }

}
