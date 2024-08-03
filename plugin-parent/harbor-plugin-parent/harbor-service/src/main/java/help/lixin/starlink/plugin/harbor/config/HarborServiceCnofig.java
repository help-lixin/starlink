package help.lixin.starlink.plugin.harbor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.harbor.mapper.HarborProjectMapper;
import help.lixin.starlink.plugin.harbor.service.IHarborLoginfoService;
import help.lixin.starlink.plugin.harbor.service.IHarborProjectService;
import help.lixin.starlink.plugin.harbor.service.impl.HarborHarborProjectService;
import help.lixin.starlink.plugin.harbor.service.impl.HarborLoginfoService;

/**
 * @Author: 伍岳林
 * @Date: 2023/5/18 4:01 下午
 * @Description
 */
@Configuration
public class HarborServiceCnofig {

    @Bean
    @ConditionalOnMissingBean(name = "harborProjectService")
    public IHarborProjectService harborProjectService(
        @Autowired @Qualifier("harborServiceFactory") AbstractServiceFactory harborServiceFactory,
        HarborProjectMapper projectMapper, QueryTemplate queryTemplate) {
        return new HarborHarborProjectService(harborServiceFactory, projectMapper, queryTemplate);
    }

    @Bean
    @ConditionalOnMissingBean(name = "harborLoginfoService")
    public IHarborLoginfoService harborLoginfoService(
        @Autowired @Qualifier("harborServiceFactory") AbstractServiceFactory harborServiceFactory) {
        return new HarborLoginfoService(harborServiceFactory);
    }
}
