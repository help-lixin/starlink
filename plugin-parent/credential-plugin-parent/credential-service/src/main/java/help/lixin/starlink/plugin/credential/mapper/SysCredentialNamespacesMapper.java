package help.lixin.starlink.plugin.credential.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.credential.domain.SysCredentialNamespaces;
import help.lixin.starlink.plugin.credential.dto.SysCredentialNameSpaceDTO;

import java.util.List;

public interface SysCredentialNamespacesMapper extends BaseMapper<SysCredentialNamespaces> {

    int insertSelective(SysCredentialNamespaces record);

    List<SysCredentialNameSpaceDTO> queryNameSpaceOptionList(String instanceCode);

}