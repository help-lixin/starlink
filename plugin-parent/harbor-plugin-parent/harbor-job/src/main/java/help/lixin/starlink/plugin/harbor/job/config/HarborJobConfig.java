package help.lixin.starlink.plugin.harbor.job.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.harbor.job.HarborProjectJob;
import help.lixin.starlink.plugin.harbor.job.service.HarborProjectJobService;
import help.lixin.starlink.plugin.harbor.mapper.HarborProjectMapper;

/**
 * @Author: 伍岳林
 * @Date: 2023/6/2 4:14 下午
 * @Description
 */
@Configuration
public class HarborJobConfig {

    @Bean
    @ConditionalOnMissingBean
    public HarborProjectJobService harborProjectJobService(
        @Autowired @Qualifier("harborServiceFactory") AbstractServiceFactory harborServiceFactory,
        HarborProjectMapper projectMapper) {
        return new HarborProjectJobService(harborServiceFactory, projectMapper);
    }

    @Bean
    @ConditionalOnMissingBean
    public HarborProjectJob harborProjectJob(HarborProjectJobService harborProjectJobService) {
        return new HarborProjectJob(harborProjectJobService);
    }
}
