package help.lixin.starlink.plugin.github.action.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.core.pipeline.action.Action;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.plugin.credential.service.ICredentialService;
import help.lixin.starlink.plugin.github.action.GithubCloneAction;
import help.lixin.starlink.plugin.github.action.service.IGithubCredentialService;
import help.lixin.starlink.plugin.github.action.service.impl.GithubCredentialService;

@Configuration
public class GithubActionConfig {

    // 源代码目录
    @Value("${source.directory:null}")
    private String sourceDirectory;

    @Bean
    @ConditionalOnMissingBean(IGithubCredentialService.class)
    public IGithubCredentialService githubCredentialService( //
        @Autowired(required = true) ICredentialService credentialService) {
        return new GithubCredentialService(credentialService);
    }

    @Bean
    public Action githubCloneAction(IExpressionService expressionService, //
        IGithubCredentialService githubCredentialService //
    ) {
        if (null != sourceDirectory && !sourceDirectory.endsWith("/")) {
            sourceDirectory = sourceDirectory + "/";
        }
        return new GithubCloneAction(sourceDirectory, expressionService, githubCredentialService);
    }
}
