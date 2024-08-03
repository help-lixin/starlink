package help.lixin.starlink.plugin.credential.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import help.lixin.starlink.plugin.credential.domain.SysCredentialCommon;
import help.lixin.starlink.plugin.credential.dto.SysCredentialPageListDTO;

public interface SysCredentialCommonMapper extends BaseMapper<SysCredentialCommon> {
    int insertCommon(SysCredentialCommon record);

    List<SysCredentialCommon> list(SysCredentialPageListDTO sysCredentialPageListDTO);

    List<Map> queryListById(Long id);

    List<Map> queryOptionList(@Param("instanceCode") String instanceCode, @Param("nameSpace") String nameSpace,
        @Param("filterType") String filterType);

    List<SysCredentialCommon> queryListByInstanceCode(String instanceCode);

    List<SysCredentialCommon> selectAll();

    int removeById(Long id);
}