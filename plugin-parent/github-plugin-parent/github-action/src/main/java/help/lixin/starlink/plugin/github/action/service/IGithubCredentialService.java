package help.lixin.starlink.plugin.github.action.service;

import help.lixin.starlink.plugin.github.action.entity.CredentialsProvider;

public interface IGithubCredentialService {

    CredentialsProvider queryById(String credentialId);

}
