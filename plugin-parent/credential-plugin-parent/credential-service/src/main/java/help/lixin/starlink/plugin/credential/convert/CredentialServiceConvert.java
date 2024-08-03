package help.lixin.starlink.plugin.credential.convert;

import help.lixin.starlink.plugin.credential.domain.opaque.SysOpaqueCreateCredentialEvent;
import help.lixin.starlink.plugin.credential.domain.opaque.SysOpaqueUpdateCredentialEvent;
import help.lixin.starlink.plugin.credential.domain.tls.SysTLSCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.tls.SysTLSCredentialUpdateEvent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import help.lixin.starlink.plugin.credential.domain.*;
import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.pwd.SysUserNamePasswordCredentialUpdateEvent;
import help.lixin.starlink.plugin.credential.domain.secret.SysSecretCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.secret.SysSecretCredentialUpdateEvent;
import help.lixin.starlink.plugin.credential.domain.ssh.SysSshCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.ssh.SysSshCredentialUpdateEvent;
import help.lixin.starlink.plugin.credential.domain.token.SysTokenCredentialCreateEvent;
import help.lixin.starlink.plugin.credential.domain.token.SysTokenCredentialUpdateEvent;
import help.lixin.starlink.plugin.credential.dto.*;

@Mapper
public interface CredentialServiceConvert {

    /** ================= userName Password =================*/
    @Mapping(target = "username",source = "userName")
    SysUserNamePasswordCredentialCreateEvent createPwd(SysCredentialUsernamePasswordDTO sysCredentialUsernamePasswordDTO);
    @Mapping(target = "username",source = "userName")
    SysUserNamePasswordCredentialUpdateEvent updatePwd(SysCredentialUsernamePasswordDTO sysCredentialUsernamePasswordDTO);

    /** ================= secret =================*/
    SysSecretCredentialCreateEvent createSecret(SysCredentialTextDTO sysCredentialTextDTO);
    SysSecretCredentialUpdateEvent updateSecret(SysCredentialTextDTO sysCredentialTextDTO);

    /** ================= ssh =================*/
    @Mapping(target = "username",source = "userName")
    @Mapping(target = "privatekey",source = "privateKey")
    SysSshCredentialCreateEvent createSsh(SysCredentialSshDTO sysCredentialSshDTO);

    @Mapping(target = "username",source = "userName")
    @Mapping(target = "privatekey",source = "privateKey")
    SysSshCredentialUpdateEvent updateSsh(SysCredentialSshDTO sysCredentialSshDTO);

    /** ================= token =================*/
    SysTokenCredentialCreateEvent createToken(SysCredentialTokenDTO sysCredentialTokenDTO);
    SysTokenCredentialUpdateEvent updateToken(SysCredentialTokenDTO sysCredentialTokenDTO);

    /** ================= TLS =================*/
    SysTLSCredentialCreateEvent createTLS(SysCredentialTlsDTO sysCredentialTlsDTO);

    SysTLSCredentialUpdateEvent updateTLS(SysCredentialTlsDTO sysCredentialTlsDTO);

    /** ================= Opaque =================*/
    SysOpaqueCreateCredentialEvent createOpaque(SysCredentialOpaqueDTO sysCredentialOpaqueDTO);
    SysOpaqueUpdateCredentialEvent updateOpaque(SysCredentialOpaqueDTO sysCredentialOpaqueDTO);

    SysCredentialUsernamePassword convertDomain(SysCredentialUsernamePasswordDTO sysCredentialUsernamePasswordDTO);
    SysCredentialText convertDomain(SysCredentialTextDTO sysCredentialTextDTO);
    SysCredentialSsh convertDomain(SysCredentialSshDTO sysCredentialSshDTO);
    SysCredentialCommon convertDomain(SysCredentialCommonDTO sysCredentialCommonDTO);
    SysCredentialToken convertDomain(SysCredentialTokenDTO sysCredentialTokenDTO);
    SysCredentialOpaque convertDomain(SysCredentialOpaqueDTO sysCredentialOpaqueDTO);
    SysCredentialTls convertDomain(SysCredentialTlsDTO sysCredentialTlsDTO);


    SysTokenCredentialCreateEvent createToken(SysCredentialToken credentialCommon);
    SysSecretCredentialCreateEvent createSecret(SysCredentialText credentialCommon);
    @Mapping(target = "username",source = "userName")
    SysUserNamePasswordCredentialCreateEvent createPwd(SysCredentialUsernamePassword credentialCommon);
    @Mapping(target = "username",source = "userName")
    @Mapping(target = "privatekey",source = "privateKey")
    SysSshCredentialCreateEvent createSsh(SysCredentialSsh credentialCommon);

}
