package help.lixin.starlink.plugin.gitlab.action.service;

import help.lixin.starlink.plugin.gitlab.action.entity.CredentialsProvider;

public interface IGitlabCredentialService {

    CredentialsProvider queryById(String credentialId);
}
