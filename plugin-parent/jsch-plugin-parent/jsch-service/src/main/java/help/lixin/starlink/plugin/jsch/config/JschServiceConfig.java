package help.lixin.starlink.plugin.jsch.config;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.jsch.mapper.SshHostsMapper;
import help.lixin.starlink.plugin.jsch.mapper.SshLabelMapper;
import help.lixin.starlink.plugin.jsch.service.IGenerateSshHostsService;
import help.lixin.starlink.plugin.jsch.service.IQuerySshHostService;
import help.lixin.starlink.plugin.jsch.service.ISshLabelService;
import help.lixin.starlink.plugin.jsch.service.impl.GenerateSshHostsService;
import help.lixin.starlink.plugin.jsch.service.impl.QuerySshHostService;
import help.lixin.starlink.plugin.jsch.service.impl.SshLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JschServiceConfig {

    @Bean
    @ConditionalOnMissingBean(name = "querySshHostService")
    public IQuerySshHostService querySshHostService(SshHostsMapper sshHostsMapper) {
        return new QuerySshHostService(sshHostsMapper);
    }

    @Bean
    @ConditionalOnMissingBean(name = "generateSshHostsService")
    public IGenerateSshHostsService generateSshHostsService(@Autowired @Qualifier("jschServiceFactory") //
                                                      AbstractServiceFactory jschServiceFactory) {
        return new GenerateSshHostsService(jschServiceFactory);
    }

    @Bean
    @ConditionalOnMissingBean(name = "sshLabelService")
    public ISshLabelService sshLabelService(SshLabelMapper SshLabelMapper,
                                              SshHostsMapper SshHostsMapper,
                                              QueryTemplate queryTemplate){
        return new SshLabelService(SshLabelMapper, SshHostsMapper,queryTemplate);
    }
}
