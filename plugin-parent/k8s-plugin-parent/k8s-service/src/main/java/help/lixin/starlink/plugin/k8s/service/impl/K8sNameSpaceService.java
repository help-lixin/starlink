package help.lixin.starlink.plugin.k8s.service.impl;

import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.k8s.convert.K8sNameSpaceServiceConvert;
import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.dto.base.NameSpacePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.namespace.SaveNameSpaceDTO;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesNameSpaceMapper;
import help.lixin.starlink.plugin.k8s.service.IK8SNameSpaceService;
import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.NamespaceBuilder;
import io.fabric8.kubernetes.api.model.NamespaceStatusBuilder;
import io.fabric8.kubernetes.api.model.ObjectMetaBuilder;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/16 上午11:16
 * @Description
 */
public class K8sNameSpaceService implements IK8SNameSpaceService {

    private KubernetesNameSpaceMapper kubernetesNameSpaceMapper;
    private AbstractServiceFactory k8sServiceFactory;
    private QueryTemplate queryTemplate;

    @Override
    public Boolean save(SaveNameSpaceDTO saveNameSpaceDTO) {
        String instanceCode = saveNameSpaceDTO.getInstanceCode();
        String name = saveNameSpaceDTO.getName();

        KubernetesNameSpace dbKubernetesNameSpace =
            kubernetesNameSpaceMapper.queryNameSpaceByNameAndInstanceCode(instanceCode, name);
        if (dbKubernetesNameSpace != null) {
            throw new ServiceException("该命名空间已存在，请确认后重试");
        }

        K8sNameSpaceServiceConvert mapper = Mappers.getMapper(K8sNameSpaceServiceConvert.class);
        KubernetesNameSpace kubernetesNameSpace = mapper.convert(saveNameSpaceDTO);
        kubernetesNameSpaceMapper.insertSelective(kubernetesNameSpace);

        NameSpaceApiService instance = k8sServiceFactory.getInstance(instanceCode, NameSpaceApiService.class);
        Namespace namespace = new NamespaceBuilder().withApiVersion("v1").withKind("Namespace")
            .withStatus(new NamespaceStatusBuilder().withPhase("Active").build())
            .withMetadata(new ObjectMetaBuilder().withName(name).build()).build();
        Namespace nameSpace = instance.createNameSpace(namespace);

        return nameSpace != null;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean deleteNameSpace(Long id, String userName) {

        KubernetesNameSpace kubernetesNameSpace = kubernetesNameSpaceMapper.selectById(id);
        kubernetesNameSpace.setIsDel(1);
        kubernetesNameSpaceMapper.updateById(kubernetesNameSpace);

        String instanceCode = kubernetesNameSpace.getInstanceCode();
        NameSpaceApiService instance = k8sServiceFactory.getInstance(instanceCode, NameSpaceApiService.class);

        return instance.deleteNameSpace(kubernetesNameSpace.getName());
    }

    @Override
    public PageResponse<KubernetesNameSpace> queryPageList(NameSpacePageListDTO pageDTO) {
        return queryTemplate.execute(pageDTO, () -> {
            kubernetesNameSpaceMapper.queryPageList(pageDTO);
        });
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean changeStatus(Integer status, Long id, String userName) {
        KubernetesNameSpace kubernetesNameSpace = kubernetesNameSpaceMapper.selectById(id);
        if (kubernetesNameSpace == null) {
            throw new ServiceException("该id不存在，请检查后重试");
        }
        kubernetesNameSpace.setStatus(status);
        kubernetesNameSpace.setUpdateBy(userName);
        return kubernetesNameSpaceMapper.updateById(kubernetesNameSpace) > 0;
    }

    @Override
    public Boolean nameIsExist(String instanceCode, String name) {
        return kubernetesNameSpaceMapper.queryNameSpaceByNameAndInstanceCode(instanceCode, name) != null;
    }

    public K8sNameSpaceService(AbstractServiceFactory k8sServiceFactory, QueryTemplate queryTemplate,
        KubernetesNameSpaceMapper kubernetesNameSpaceMapper) {
        this.k8sServiceFactory = k8sServiceFactory;
        this.queryTemplate = queryTemplate;
        this.kubernetesNameSpaceMapper = kubernetesNameSpaceMapper;
    }
}
