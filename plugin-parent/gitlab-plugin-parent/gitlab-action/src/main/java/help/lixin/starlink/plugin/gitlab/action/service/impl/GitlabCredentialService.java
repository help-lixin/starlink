package help.lixin.starlink.plugin.gitlab.action.service.impl;

import help.lixin.starlink.plugin.credential.domain.SysCredentialCommon;
import help.lixin.starlink.plugin.credential.domain.SysCredentialSsh;
import help.lixin.starlink.plugin.credential.domain.SysCredentialToken;
import help.lixin.starlink.plugin.credential.domain.SysCredentialUsernamePassword;
import help.lixin.starlink.plugin.credential.service.ICredentialService;
import help.lixin.starlink.plugin.gitlab.action.entity.CredentialsProvider;
import help.lixin.starlink.plugin.gitlab.action.entity.SSHCredentialsProvider;
import help.lixin.starlink.plugin.gitlab.action.entity.TokenCredentialsProvider;
import help.lixin.starlink.plugin.gitlab.action.entity.UsernamePasswordCredentialsProvider;
import help.lixin.starlink.plugin.gitlab.action.service.IGitlabCredentialService;

public class GitlabCredentialService implements IGitlabCredentialService {

    private ICredentialService credentialService;

    public GitlabCredentialService(ICredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @Override
    public CredentialsProvider queryById(String credentialId) {
        SysCredentialCommon sysCredentialCommon = credentialService.queryDecodeById(Long.parseLong(credentialId));
        if (null != sysCredentialCommon) {
            if (sysCredentialCommon instanceof SysCredentialUsernamePassword) {
                SysCredentialUsernamePassword sysCredentialUsernamePassword =
                    (SysCredentialUsernamePassword)sysCredentialCommon;

                UsernamePasswordCredentialsProvider credentialsProvider = new UsernamePasswordCredentialsProvider();
                credentialsProvider.setUsername(sysCredentialUsernamePassword.getUserName());
                credentialsProvider.setPassword(sysCredentialUsernamePassword.getPassword());
                return credentialsProvider;
            } else if (sysCredentialCommon instanceof SysCredentialToken) {
                SysCredentialToken sysCredentialToken = (SysCredentialToken)sysCredentialCommon;

                TokenCredentialsProvider tokenCredentialsProvider = new TokenCredentialsProvider();
                tokenCredentialsProvider.setToken(sysCredentialToken.getToken());
                return tokenCredentialsProvider;
            } else if (sysCredentialCommon instanceof SysCredentialSsh) {
                SysCredentialSsh sysCredentialSsh = (SysCredentialSsh)sysCredentialCommon;
                SSHCredentialsProvider sshCredentialsProvider = new SSHCredentialsProvider();
                sshCredentialsProvider.setPrivateKey(sysCredentialSsh.getPrivateKey());
                sshCredentialsProvider.setPrivateKeyPassphrase(sysCredentialSsh.getPassphrase());
                return sshCredentialsProvider;
            }
        }
        return null;
    }

}
