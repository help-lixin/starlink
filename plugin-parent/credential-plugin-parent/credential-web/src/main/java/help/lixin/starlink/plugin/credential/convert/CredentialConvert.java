package help.lixin.starlink.plugin.credential.convert;

import help.lixin.starlink.plugin.credential.dto.*;
import help.lixin.starlink.plugin.credential.request.*;
import org.mapstruct.Mapper;

@Mapper
public interface CredentialConvert {

    SysCredentialSshDTO convert(SSHCredentialVO sshCredentialVO);
    SysCredentialTextDTO convert(SecretCredentialVO secretCredentialVO);
    SysCredentialUsernamePasswordDTO convert(UserNamePasswordCredentialVO userNamePasswordCredentialVO);
    SysCredentialPageListDTO convert(SysCredentialPageListVO sysCredentialPageListVO);
    SysCredentialTokenDTO convert(TokenCredentialVO tokenCredentialVO);
    SysCredentialOpaqueDTO convert(SysCredentialOpaqueVO sysCredentialOpaqueVO);
    SysCredentialTlsDTO convert(SysCredentialTlsVO sysCredentialTlsVO);
}
