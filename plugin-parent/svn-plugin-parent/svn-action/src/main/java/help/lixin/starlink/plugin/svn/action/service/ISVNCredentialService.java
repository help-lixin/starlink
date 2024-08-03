package help.lixin.starlink.plugin.svn.action.service;

import help.lixin.starlink.plugin.svn.action.entity.CredentialsProvider;

public interface ISVNCredentialService {

    CredentialsProvider queryById(String credentialId);

}
