package help.lixin.starlink.plugin.k8s.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.k8s.domain.KubernetesDeployTemplate;
import help.lixin.starlink.plugin.k8s.dto.DeployTemplateOption;
import help.lixin.starlink.plugin.k8s.dto.deploytemplate.DeployTemplateDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KubernetesDeployTemplateMapper extends BaseMapper<KubernetesDeployTemplate> {

    KubernetesDeployTemplate getByDeployId(@Param("deployId") Long deployId);

    List<DeployTemplateOption> querydeployTemplateOptions();

    List<KubernetesDeployTemplate> pageList(DeployTemplateDTO dto);

    int removeById(Long id);

    KubernetesDeployTemplate selectByDeployName(@Param("deployName") String deployName);


    KubernetesDeployTemplate checkDeployNameUnique(@Param("deployName") String deployName);
}
