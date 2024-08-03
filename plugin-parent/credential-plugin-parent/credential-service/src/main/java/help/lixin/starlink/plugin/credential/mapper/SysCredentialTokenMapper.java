package help.lixin.starlink.plugin.credential.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import help.lixin.starlink.plugin.credential.domain.SysCredentialToken;

public interface SysCredentialTokenMapper extends BaseMapper<SysCredentialToken> {

    int insertSelective(SysCredentialToken record);

    int updateByPrimaryKeySelective(SysCredentialToken record);

    SysCredentialToken queryById(Long id);

}