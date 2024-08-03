package help.lixin.starlink.plugin.k8s.service.impl;

import java.util.Date;

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
import help.lixin.starlink.plugin.k8s.service.IK8sJobService;
import io.fabric8.kubernetes.api.model.batch.v1.Job;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/16 上午11:15
 * @Description
 */
public class K8sJobService extends K8sBaseService implements IK8sJobService {

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean deleteJob(Long id, String userName) {
        KubernetesApp dbKubernetesApp = deleteKubernetesApp(id, userName);

        String instanceCode = dbKubernetesApp.getInstanceCode();

        JobApiService instance = k8sServiceFactory.getInstance(instanceCode, JobApiService.class);

        KubernetesNameSpace kubernetesNameSpace =
            kubernetesNameSpaceMapper.selectById(dbKubernetesApp.getNameSpaceId());
        return instance.delete(kubernetesNameSpace.getName(), dbKubernetesApp.getName());
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean saveJob(K8sAppDTO k8sAppDTO) {
        Boolean result = saveKubernetesApp(k8sAppDTO);

        Long id = k8sAppDTO.getId();
        JobApiService instance = k8sServiceFactory.getInstance(k8sAppDTO.getInstanceCode(), JobApiService.class);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            Job job = objectMapper.readValue(k8sAppDTO.getJsonBody(), Job.class);
            if (id == null) {
                instance.createJob(job);
            } else {
                instance.updateJob(job);
            }
            return result;
        } catch (Exception e) {
            throw new ServiceException("调用JobAPI出现异常，请联系管理员:" + e.getMessage());
        }
    }

    @Override
    public String queryById(Long id) {
        KubernetesApp kubernetesApp = kubernetesAppMapper.queryById(id);
        JobApiService instance = k8sServiceFactory.getInstance(kubernetesApp.getInstanceCode(), JobApiService.class);
        KubernetesNameSpace kubernetesNameSpace = kubernetesNameSpaceMapper.selectById(kubernetesApp.getNameSpaceId());
        Job job = instance.queryJob(kubernetesNameSpace.getName(), kubernetesApp.getName());

        if (job == null) {
            throw new ServiceException("该job数据不存在");
        }

        try {
            ConvertUtils convertUtils = new ConvertUtils();
            job.getMetadata().setManagedFields(null);
            job.setStatus(null);
            return convertUtils.objectToYAML(job);
        } catch (JsonProcessingException e) {
            throw new ServiceException("job数据转换为YAML过程发生异常：" + e.getMessage());
        }
    }

    @Override
    public Boolean restart(Long id, String userName) {
        KubernetesApp kubernetesApp = kubernetesAppMapper.queryById(id);
        if (kubernetesApp == null) {
            throw new ServiceException("该id不存在，请确认后重试");
        }

        kubernetesApp.setUpdateBy(userName);
        kubernetesApp.setUpdateTime(new Date());
        kubernetesAppMapper.updateById(kubernetesApp);

        String instanceCode = kubernetesApp.getInstanceCode();
        JobApiService instance = k8sServiceFactory.getInstance(instanceCode, JobApiService.class);

        Long nameSpaceId = kubernetesApp.getNameSpaceId();
        KubernetesNameSpace kubernetesNameSpace = kubernetesNameSpaceMapper.selectById(nameSpaceId);

        return instance.restart(kubernetesNameSpace.getName(), kubernetesApp.getName());
    }

    public K8sJobService(AbstractServiceFactory k8sServiceFactory, QueryTemplate queryTemplate,
        KubernetesNameSpaceMapper kubernetesNameSpaceMapper, KubernetesAppMapper kubernetesAppMapper,
        K8sPodsEventPublish k8sPodsEventPublish) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.queryTemplate = queryTemplate;
        this.kubernetesNameSpaceMapper = kubernetesNameSpaceMapper;
        this.kubernetesAppMapper = kubernetesAppMapper;
        this.k8sPodsEventPublish = k8sPodsEventPublish;
    }

}
