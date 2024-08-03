package help.lixin.starlink.plugin.gitlab.action.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.core.pipeline.action.Action;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.plugin.credential.service.ICredentialService;
import help.lixin.starlink.plugin.gitlab.action.GitlabCloneAction;
import help.lixin.starlink.plugin.gitlab.action.service.IGitlabCredentialService;
import help.lixin.starlink.plugin.gitlab.action.service.impl.GitlabCredentialService;

@Configuration
public class GitlabActionConfig {
    // 源代码目录
    @Value("${source.directory:null}")
    private String sourceDirectory;

    @Bean
    @ConditionalOnMissingBean(IGitlabCredentialService.class)
    public IGitlabCredentialService gitlabCredentialService( //
        @Autowired(required = true) ICredentialService credentialService) {
        return new GitlabCredentialService(credentialService);
    }

    //
    // @Bean
    // @ConditionalOnMissingBean(name = "gitlabConfigAction")
    // public Action gitlabConfigAction() {
    // return new GitlabConfigAction();
    // }

    @Bean
    @ConditionalOnMissingBean(name = "gitlabCloneAction")
    public Action gitlabCloneAction(IExpressionService expressionService, //
        IGitlabCredentialService gitlabCredentialService) {
        if (null != sourceDirectory && !sourceDirectory.endsWith("/")) {
            sourceDirectory = sourceDirectory + "/";
        }
        return new GitlabCloneAction(sourceDirectory, expressionService, gitlabCredentialService);
    }
}
