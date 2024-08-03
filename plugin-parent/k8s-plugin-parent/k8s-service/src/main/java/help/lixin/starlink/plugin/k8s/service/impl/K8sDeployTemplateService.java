package help.lixin.starlink.plugin.k8s.service.impl;

import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.k8s.domain.KubernetesDeployTemplate;
import help.lixin.starlink.plugin.k8s.dto.DeployTemplateOption;
import help.lixin.starlink.plugin.k8s.dto.deploytemplate.CreateDeployTemplateDTO;
import help.lixin.starlink.plugin.k8s.dto.deploytemplate.DeployTemplateDTO;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesDeployTemplateMapper;
import help.lixin.starlink.plugin.k8s.service.IK8sDeployTemplateService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class K8sDeployTemplateService implements IK8sDeployTemplateService {

    private KubernetesDeployTemplateMapper kubernetesDeployTemplateMapper;

    private QueryTemplate queryTemplate;

    public K8sDeployTemplateService(QueryTemplate queryTemplate, //
                                    KubernetesDeployTemplateMapper kubernetesDeployTemplateMapper) {
        this.queryTemplate = queryTemplate;
        this.kubernetesDeployTemplateMapper = kubernetesDeployTemplateMapper;
    }


    public List<DeployTemplateOption> querydeployTemplateOptions() {
        return kubernetesDeployTemplateMapper.querydeployTemplateOptions();
    }


    @Override
    public KubernetesDeployTemplate get(Long deployId) {
        return kubernetesDeployTemplateMapper.selectById(deployId);
    }

    @Override
    public PageResponse<KubernetesDeployTemplate> queryPageList(DeployTemplateDTO dto) {
        return queryTemplate.execute(dto, () -> {
            kubernetesDeployTemplateMapper.pageList(dto);
        });
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean delete(Long id) {
        return kubernetesDeployTemplateMapper.removeById(id) > 0 ? true : false;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean changeStatus(Long id, Integer status, String createBy) {
        KubernetesDeployTemplate kubernetesDeployTemplate = kubernetesDeployTemplateMapper.selectById(id);
        if (kubernetesDeployTemplate == null) {
            throw new ServiceException("该id不存在，请确认后重试");
        }
        kubernetesDeployTemplate.setStatus(status);
        kubernetesDeployTemplate.setUpdateTime(new Date());
        kubernetesDeployTemplate.setUpdateBy(createBy);
        return kubernetesDeployTemplateMapper.updateById(kubernetesDeployTemplate) > 0;
    }

    @Override
    public Boolean checkDeployNameUnique(CreateDeployTemplateDTO dto) {
        Long instanceId = null == dto.getDeployId() ? -1L : dto.getDeployId();
        KubernetesDeployTemplate kubernetesDeployTemplate = kubernetesDeployTemplateMapper.checkDeployNameUnique(dto.getDeployName());
        if (null != kubernetesDeployTemplate && kubernetesDeployTemplate.getDeployId().longValue() != instanceId.longValue()) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Integer create(CreateDeployTemplateDTO dto) {
        Long deployId = dto.getDeployId();
        if (deployId == null) {
            KubernetesDeployTemplate entity = new KubernetesDeployTemplate();
            entity.setDeployName(dto.getDeployName());
            entity.setYamlContent(dto.getYamlContent());
            entity.setRemark(dto.getRemark());
            entity.setStatus(dto.getStatus());
            entity.setCreateBy(dto.getCreateBy());
            entity.setUpdateBy(dto.getUpdateBy());
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            return kubernetesDeployTemplateMapper.insert(entity);
        } else {
            KubernetesDeployTemplate entity = kubernetesDeployTemplateMapper.selectById(deployId);
            entity.setDeployName(dto.getDeployName());
            entity.setYamlContent(dto.getYamlContent());
            entity.setRemark(dto.getRemark());
            entity.setStatus(dto.getStatus());
            entity.setCreateBy(dto.getCreateBy());
            entity.setUpdateBy(dto.getUpdateBy());
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            return kubernetesDeployTemplateMapper.updateById(entity);
        }
    }
}
