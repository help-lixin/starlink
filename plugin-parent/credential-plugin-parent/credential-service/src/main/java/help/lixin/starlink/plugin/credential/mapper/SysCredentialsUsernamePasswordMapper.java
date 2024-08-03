package help.lixin.starlink.plugin.credential.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import help.lixin.starlink.plugin.credential.domain.SysCredentialUsernamePassword;

public interface SysCredentialsUsernamePasswordMapper extends BaseMapper<SysCredentialUsernamePassword> {

    int insertSelective(SysCredentialUsernamePassword record);

    int updateByPrimaryKeySelective(SysCredentialUsernamePassword record);

    SysCredentialUsernamePassword queryById(Long id);

}