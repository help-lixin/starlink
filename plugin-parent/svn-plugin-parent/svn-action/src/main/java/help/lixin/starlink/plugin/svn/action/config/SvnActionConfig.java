package help.lixin.starlink.plugin.svn.action.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.core.pipeline.action.Action;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.plugin.credential.service.ICredentialService;
import help.lixin.starlink.plugin.svn.action.SvnCheckoutAction;
import help.lixin.starlink.plugin.svn.action.service.ISVNCredentialService;
import help.lixin.starlink.plugin.svn.action.service.impl.SVNCredentialService;

@Configuration
public class SvnActionConfig {

    // 源代码目录
    @Value("${source.directory:null}")
    private String sourceDirectory;

    @Bean
    @ConditionalOnMissingBean
    public ISVNCredentialService
        svnCredentialService(@Autowired(required = true) ICredentialService credentialService) {
        return new SVNCredentialService(credentialService);
    }

    @Bean
    @ConditionalOnMissingBean(name = "svnCheckoutAction")
    public Action svnCheckoutAction(IExpressionService expressionService, //
        ISVNCredentialService svnCredentialService) {
        if (null != sourceDirectory && !sourceDirectory.endsWith("/")) {
            sourceDirectory = sourceDirectory + "/";
        }
        return new SvnCheckoutAction(sourceDirectory, svnCredentialService, expressionService);
    }
}
