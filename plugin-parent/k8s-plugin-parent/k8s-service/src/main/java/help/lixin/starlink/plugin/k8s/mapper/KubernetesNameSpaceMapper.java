package help.lixin.starlink.plugin.k8s.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.dto.base.NameSpacePageListDTO;
import help.lixin.starlink.plugin.k8s.dto.namespace.NameSpaceOptionDTO;

public interface KubernetesNameSpaceMapper extends BaseMapper<KubernetesNameSpace> {

    int insertSelective(KubernetesNameSpace record);

    List<KubernetesNameSpace> queryPageList(NameSpacePageListDTO nameSpacePageListDTO);

    List<KubernetesNameSpace> queryInNameSpace(List<String> nameSpaces);

    List<KubernetesNameSpace> queryNameSpaceByInstanceCode(String instanceCode);

    List<NameSpaceOptionDTO> queryNameSpaceOptionByInstanceCode(String instanceCode);

    Date queryCheckPointDateTime();

    KubernetesNameSpace queryNameSpaceByNameAndInstanceCode(@Param("instanceCode") String instanceCode,
        @Param("name") String name);
}