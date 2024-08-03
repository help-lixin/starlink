package help.lixin.starlink.plugin.credential.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import help.lixin.starlink.plugin.credential.domain.SysCredentialSsh;

public interface SysCredentialsSshMapper extends BaseMapper<SysCredentialSsh> {

    int insertSelective(SysCredentialSsh record);

    int updateByPrimaryKeySelective(SysCredentialSsh record);

    SysCredentialSsh queryById(Long id);
}