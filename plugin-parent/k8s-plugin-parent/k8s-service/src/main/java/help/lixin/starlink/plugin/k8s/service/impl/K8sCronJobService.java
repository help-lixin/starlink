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
import help.lixin.starlink.plugin.k8s.service.IK8sCronJobService;
import io.fabric8.kubernetes.api.model.batch.v1.CronJob;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/16 上午11:14
 * @Description
 */
public class K8sCronJobService extends K8sBaseService implements IK8sCronJobService {

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean deleteCronJob(Long id, String userName) {
        KubernetesApp dbKubernetesApp = deleteKubernetesApp(id, userName);

        String instanceCode = dbKubernetesApp.getInstanceCode();

        CronJobApiService instance = k8sServiceFactory.getInstance(instanceCode, CronJobApiService.class);

        KubernetesNameSpace kubernetesNameSpace =
            kubernetesNameSpaceMapper.selectById(dbKubernetesApp.getNameSpaceId());
        return instance.deleteCronJob(kubernetesNameSpace.getName(), dbKubernetesApp.getName());
    }

    @Override
    public Boolean changeRunStatus(Integer status, Long id, String userName) {
        KubernetesApp kubernetesApp = kubernetesAppMapper.queryById(id);
        if (kubernetesApp == null) {
            throw new ServiceException("该id不存在，请检查后重试");
        }
        kubernetesApp.setStatus(0);
        kubernetesApp.setUpdateBy(userName);
        kubernetesAppMapper.updateById(kubernetesApp);

        CronJobApiService instance =
            k8sServiceFactory.getInstance(kubernetesApp.getInstanceCode(), CronJobApiService.class);

        KubernetesNameSpace kubernetesNameSpace = kubernetesNameSpaceMapper.selectById(kubernetesApp.getNameSpaceId());
        if (status == 0) {
            instance.pause(kubernetesNameSpace.getName(), kubernetesApp.getName());
        } else {
            instance.start(kubernetesNameSpace.getName(), kubernetesApp.getName());
        }

        return true;
    }

    @Override
    public String queryById(Long id) {
        KubernetesApp kubernetesApp = kubernetesAppMapper.queryById(id);
        CronJobApiService instance =
            k8sServiceFactory.getInstance(kubernetesApp.getInstanceCode(), CronJobApiService.class);
        KubernetesNameSpace kubernetesNameSpace = kubernetesNameSpaceMapper.selectById(kubernetesApp.getNameSpaceId());
        CronJob cronJob = instance.queryCronJob(kubernetesNameSpace.getName(), kubernetesApp.getName());

        if (cronJob == null) {
            throw new ServiceException("该cronjob数据不存在");
        }

        try {
            ConvertUtils convertUtils = new ConvertUtils();
            cronJob.getMetadata().setManagedFields(null);
            cronJob.setStatus(null);
            return convertUtils.objectToYAML(cronJob);
        } catch (JsonProcessingException e) {
            throw new ServiceException("cronjob数据转换为YAML过程发生异常：" + e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean saveCronJob(K8sAppDTO k8sAppDTO) {
        Boolean result = saveKubernetesApp(k8sAppDTO);

        Long id = k8sAppDTO.getId();
        CronJobApiService instance =
            k8sServiceFactory.getInstance(k8sAppDTO.getInstanceCode(), CronJobApiService.class);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            CronJob cronJob = objectMapper.readValue(k8sAppDTO.getJsonBody(), CronJob.class);
            if (id == null) {
                instance.createCronJob(cronJob);
            } else {
                instance.updateCronJob(cronJob);
            }
            return result;
        } catch (Exception e) {
            throw new ServiceException("调用CronJobAPI出现异常，请联系管理员:" + e.getMessage());
        }
    }

    @Override
    public Boolean pause(Long id) {
        KubernetesApp kubernetesApp = kubernetesAppMapper.queryById(id);
        CronJobApiService instance =
            k8sServiceFactory.getInstance(kubernetesApp.getInstanceCode(), CronJobApiService.class);
        KubernetesNameSpace kubernetesNameSpace = kubernetesNameSpaceMapper.selectById(kubernetesApp.getNameSpaceId());
        return instance.pause(kubernetesNameSpace.getName(), kubernetesApp.getName());
    }

    @Override
    public Boolean start(Long id) {
        return null;
    }

    public K8sCronJobService(AbstractServiceFactory k8sServiceFactory, QueryTemplate queryTemplate,
        KubernetesNameSpaceMapper kubernetesNameSpaceMapper, KubernetesAppMapper kubernetesAppMapper,
        K8sPodsEventPublish k8sPodsEventPublish) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.queryTemplate = queryTemplate;
        this.kubernetesNameSpaceMapper = kubernetesNameSpaceMapper;
        this.kubernetesAppMapper = kubernetesAppMapper;
        this.k8sPodsEventPublish = k8sPodsEventPublish;
    }
}
