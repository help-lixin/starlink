package help.lixin.starlink.plugin.nacos.mapper;

import help.lixin.starlink.core.dto.EnvDTO;
import help.lixin.starlink.plugin.nacos.api.dto.config.NacosPageListDetailConfigResponse;
import help.lixin.starlink.plugin.nacos.api.model.config.NacosPageListConfig;
import help.lixin.starlink.plugin.nacos.domain.NacosConfigCenter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NacosConfigCenterMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NacosConfigCenter record);

    int insertSelective(NacosConfigCenter record);

    NacosConfigCenter selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NacosConfigCenter record);

    int updateByPrimaryKeyWithBLOBs(NacosConfigCenter record);

    int updateByPrimaryKey(NacosConfigCenter record);

    int updateByGroupAndDataId(NacosConfigCenter nacosConfigCenter);

    NacosConfigCenter queryByGroupAndDataId(@Param("group") String group, @Param("dataId") String dataId);

    int removeConfig(List<Long> ids);

    List<NacosPageListDetailConfigResponse> queryByEnv(EnvDTO envDTO);

    List<NacosPageListDetailConfigResponse> queryPageList(NacosPageListConfig nacosPageListConfig);
}