package help.lixin.starlink.plugin.credential.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import help.lixin.starlink.plugin.credential.domain.SysCredentialTls;

public interface SysCredentialTlsMapper extends BaseMapper<SysCredentialTls> {

    int insertSelective(SysCredentialTls record);

    SysCredentialTls queryById(Long id);
}