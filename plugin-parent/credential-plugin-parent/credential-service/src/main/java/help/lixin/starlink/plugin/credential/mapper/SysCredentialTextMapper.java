package help.lixin.starlink.plugin.credential.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.credential.domain.SysCredentialText;

public interface SysCredentialTextMapper extends BaseMapper<SysCredentialText> {

    int insertSelective(SysCredentialText record);

    int updateByPrimaryKeySelective(SysCredentialText record);

}