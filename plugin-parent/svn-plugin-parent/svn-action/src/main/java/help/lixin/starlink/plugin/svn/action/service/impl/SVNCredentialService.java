package help.lixin.starlink.plugin.svn.action.service.impl;

import help.lixin.starlink.plugin.credential.domain.SysCredentialCommon;
import help.lixin.starlink.plugin.credential.domain.SysCredentialUsernamePassword;
import help.lixin.starlink.plugin.credential.service.ICredentialService;
import help.lixin.starlink.plugin.svn.action.entity.CredentialsProvider;
import help.lixin.starlink.plugin.svn.action.entity.UsernamePasswordCredentialsProvider;
import help.lixin.starlink.plugin.svn.action.service.ISVNCredentialService;

/**
 * 去数据库查询凭证信息
 */
public class SVNCredentialService implements ISVNCredentialService {

    private ICredentialService credentialService;

    public SVNCredentialService(ICredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @Override
    public CredentialsProvider queryById(String credentialId) {
        SysCredentialCommon sysCredentialCommon = credentialService.queryDecodeById(Long.parseLong(credentialId));
        if (null != sysCredentialCommon) {
            if (sysCredentialCommon instanceof SysCredentialUsernamePassword) {
                SysCredentialUsernamePassword sysCredentialUsernamePassword = (SysCredentialUsernamePassword) sysCredentialCommon;

                UsernamePasswordCredentialsProvider provider = new UsernamePasswordCredentialsProvider();
                provider.setUsername(sysCredentialUsernamePassword.getUserName());
                provider.setPassword(sysCredentialUsernamePassword.getPassword());
                return provider;
            }
        }
        return null;
    }
}
