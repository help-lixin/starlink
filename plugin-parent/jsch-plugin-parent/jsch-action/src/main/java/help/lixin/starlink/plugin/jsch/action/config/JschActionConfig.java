package help.lixin.starlink.plugin.jsch.action.config;

import help.lixin.core.pipeline.action.Action;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jsch.action.SSHDeployAction;
import help.lixin.starlink.plugin.jsch.action.SSHDockerBuildAction;
import help.lixin.starlink.plugin.jsch.action.SSHDockerLoginConfigAction;
import help.lixin.starlink.plugin.jsch.action.SSHShellAction;
import help.lixin.starlink.plugin.jsch.service.IQuerySshHostService;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JschActionConfig {

    @Bean
    @ConditionalOnMissingBean(name = "sshDeployAction")
    public Action sshDeployAction(@Autowired @Qualifier("jschServiceFactory") //
    AbstractServiceFactory jschServiceFactory, //
        IExpressionService expressionService, //
        IQuerySshHostService queryHostService) {
        return new SSHDeployAction(jschServiceFactory, expressionService, queryHostService);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sshShellAction")
    public Action sshShellAction(@Autowired @Qualifier("jschServiceFactory") //
    AbstractServiceFactory jschServiceFactory, //
        IExpressionService expressionService, //
        IQuerySshHostService querySshHostService) {
        return new SSHShellAction(jschServiceFactory, expressionService, querySshHostService);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sshDockerBuildAction")
    public Action sshDockerBuildAction(@Autowired @Qualifier("jschServiceFactory") //
                                       AbstractServiceFactory jschServiceFactory, //
                                       IExpressionService expressionService, //
                                       StringEncryptor stringEncryptor, //
                                       IQuerySshHostService querySshHostService) {
        return new SSHDockerBuildAction(jschServiceFactory, expressionService, stringEncryptor, querySshHostService);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sshDockerLoginConfigAction")
    public Action sshDockerLoginConfigAction(@Autowired @Qualifier("jschServiceFactory") //
                                             AbstractServiceFactory jschServiceFactory, StringEncryptor stringEncryptor //
    ) {
        return new SSHDockerLoginConfigAction(jschServiceFactory, stringEncryptor);
    }
}
