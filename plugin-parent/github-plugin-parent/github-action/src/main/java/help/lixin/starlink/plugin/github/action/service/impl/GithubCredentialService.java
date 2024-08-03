package help.lixin.starlink.plugin.github.action.service.impl;

import help.lixin.starlink.plugin.credential.domain.SysCredentialCommon;
import help.lixin.starlink.plugin.credential.domain.SysCredentialSsh;
import help.lixin.starlink.plugin.credential.domain.SysCredentialToken;
import help.lixin.starlink.plugin.credential.service.ICredentialService;
import help.lixin.starlink.plugin.github.action.entity.CredentialsProvider;
import help.lixin.starlink.plugin.github.action.entity.SSHCredentialsProvider;
import help.lixin.starlink.plugin.github.action.entity.TokenCredentialsProvider;
import help.lixin.starlink.plugin.github.action.service.IGithubCredentialService;

/**
 * 去数据库查询凭证信息
 */
public class GithubCredentialService implements IGithubCredentialService {

    private ICredentialService credentialService;

    public GithubCredentialService(ICredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @Override
    public CredentialsProvider queryById(String credentialId) {
        // gitbub 不支持用户名和密码模式.
        SysCredentialCommon sysCredentialCommon = credentialService.queryDecodeById(Long.parseLong(credentialId));
        if (null != sysCredentialCommon) {
            if (sysCredentialCommon instanceof SysCredentialToken) {
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
