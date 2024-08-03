package help.lixin.starlink.plugin.jenkins.api.service;

import help.lixin.starlink.plugin.jenkins.api.model.CredentialEnum;

public interface IKeyManagerService {

    Boolean createCredentials(String credentialJson, String credentialsId, CredentialEnum credentialEnum);

    Boolean deleteCredentials(String credentialsId, CredentialEnum credentialEnum);

    Boolean updateCredentials(String credentialJson, String credentialsId);

    Boolean checkCredentialsId(String credentialsId, CredentialEnum credentialEnum);

    Boolean checkGitCredentialsId(String credentialsId, String path, CredentialEnum credentialEnum, String jobId);
}
