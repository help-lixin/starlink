package help.lixin.svn.config;

import help.lixin.core.pipeline.action.Action;
import help.lixin.svn.action.SvnCheckoutAction;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SvnActionCofnig {

    @Bean
    @ConditionalOnMissingBean(name = "svnCheckoutAction")
    public Action svnCheckoutAction() {
        return new SvnCheckoutAction();
    }
}
