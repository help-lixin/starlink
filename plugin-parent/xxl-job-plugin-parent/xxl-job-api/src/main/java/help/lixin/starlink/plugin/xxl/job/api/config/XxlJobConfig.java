package help.lixin.starlink.plugin.xxl.job.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import help.lixin.starlink.plugin.xxl.job.api.mediator.CookieMediator;
import help.lixin.starlink.plugin.xxl.job.api.properties.XxlJobProperties;
import help.lixin.starlink.plugin.xxl.job.api.service.*;
import help.lixin.starlink.plugin.xxl.job.api.service.impl.JobGroupService;
import help.lixin.starlink.plugin.xxl.job.api.service.impl.JobInfoService;
import help.lixin.starlink.plugin.xxl.job.api.service.impl.JobLogService;
import help.lixin.starlink.plugin.xxl.job.api.service.impl.LoginService;

public class XxlJobConfig {
    @Value("${plugin:default}")
    private String plugin;

    @Value("${instance:default}")
    private String instance;

    @Bean
    public XxlJobProperties xxlJobProperties(Environment environment) {
        String prefix = String.format("%s.%s", plugin, instance);
        XxlJobProperties XxlJobProperties = Binder.get(environment)//
            .bind(prefix, XxlJobProperties.class)//
            .get();
        return XxlJobProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public JobFaceService harborFaceService(IJobGroupService jobGroupService, //
        IJobInfoService jobInfoService, //
        IJobLogService jobLogService, //
        ILoginService loginService, //
        XxlJobProperties xxlJobProperties) {
        return new JobFaceService(jobGroupService, jobInfoService, jobLogService, loginService, xxlJobProperties);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJobGroupService jobGroupService(XxlJobProperties properties, CookieMediator cookieMediator) {
        return new JobGroupService(properties.getUrl(), cookieMediator);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJobInfoService jobInfoService(XxlJobProperties properties, CookieMediator cookieMediator) {
        return new JobInfoService(properties.getUrl(), cookieMediator);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJobLogService jobLogService(XxlJobProperties properties, CookieMediator cookieMediator) {
        return new JobLogService(properties.getUrl(), cookieMediator);
    }

    @Bean
    @ConditionalOnMissingBean
    public ILoginService loginService(XxlJobProperties properties, CookieMediator cookieMediator) {
        return new LoginService(properties, cookieMediator);
    }

    @Bean
    @ConditionalOnMissingBean
    public CookieMediator cookieMediator() {
        return new CookieMediator();
    }
}
