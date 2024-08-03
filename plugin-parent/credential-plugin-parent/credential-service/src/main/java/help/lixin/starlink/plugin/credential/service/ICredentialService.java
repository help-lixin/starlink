package help.lixin.starlink.plugin.credential.service;

import java.util.List;

import help.lixin.starlink.plugin.credential.domain.SysCredentialCommon;
import help.lixin.starlink.plugin.credential.dto.*;
import help.lixin.response.PageResponse;

public interface ICredentialService {

    Boolean save(SysCredentialCommonDTO abstractEvent);

    List<SysCredentialOptionDTO> credentialOptionSelect(String instanceCode);

    List<CredentialOptionDTO> credentialOptionSelect(String instanceCode, String nameSpace, String filterType,
        Boolean isFilterURL);

    Boolean syncAllCredential();

    PageResponse<SysCredentialCommon> list(SysCredentialPageListDTO sysCredentialPageListDTO);

    SysCredentialCommon queryOriginDataById(Long id);

    SysCredentialCommon queryDecodeById(Long id);

    Boolean checkId(String credentialKey, String instanceCode);

    Integer changeStatus(Integer status,Long id,String createBy);

    Boolean removeCredential(Long id);

    List<SysCredentialNameSpaceDTO> queryNameSpaceList(String instanceCode);
}
