package help.lixin.starlink.plugin.credential.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.credential.domain.SysCredentialOpaque;

import java.util.List;

public interface SysCredentialOpaqueMapper extends BaseMapper<SysCredentialOpaque> {

    int insertSelective(SysCredentialOpaque record);

    List<SysCredentialOpaque> queryOpaqueList(Long id);
}