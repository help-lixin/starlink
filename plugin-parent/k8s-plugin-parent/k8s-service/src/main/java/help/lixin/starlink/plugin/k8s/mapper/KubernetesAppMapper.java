package help.lixin.starlink.plugin.k8s.mapper;

import java.util.List;

import help.lixin.starlink.plugin.k8s.domain.KubernetesApp;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import help.lixin.starlink.plugin.k8s.dto.base.BasePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.base.KubernetesAppDTO;

public interface KubernetesAppMapper extends BaseMapper<KubernetesApp> {

    int insertSelective(KubernetesApp record);

    List<KubernetesApp> queryInstanceAndNameIsExist(@Param("instanceCode") String instanceCde,
        @Param("name") String name);

    List<KubernetesApp> queryPodsByInstanceAndName(@Param("instanceCode") String instanceCde,
        @Param("name") String name);

    List<KubernetesAppDTO> queryPageList(BasePageListDTO basePageListDTO);

    KubernetesApp queryById(Long id);
}