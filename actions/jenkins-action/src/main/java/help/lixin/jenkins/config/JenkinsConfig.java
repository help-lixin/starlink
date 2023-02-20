package help.lixin.jenkins.config;

import com.cdancy.jenkins.rest.JenkinsClient;
import help.lixin.jenkins.properties.JenkinsProperties;
import help.lixin.jenkins.schedule.CrumbSchedule;
import help.lixin.jenkins.service.*;
import help.lixin.jenkins.service.impl.CrumbIssuerService;
import help.lixin.jenkins.service.impl.JobService;
import help.lixin.jenkins.service.impl.QueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({JenkinsProperties.class})
public class JenkinsConfig {

    @Bean
    @ConditionalOnMissingBean
    public JenkinsClient jenkinsClient(JenkinsProperties properties,
                                       //
                                       @Autowired(required = false) JenkinsClientCustomizer jenkinsClientCustomizer) {
        JenkinsClient client = JenkinsClient.builder() //
                .endPoint(properties.getUrl()) // Optional. Defaults to
                .credentials(properties.getUserName() + ":" + properties.getPassword()) // Optional.
                .build();
        if (null != jenkinsClientCustomizer) {
            jenkinsClientCustomizer.customizer(properties, client);
        }
        return client;
    }

    @Bean
    @ConditionalOnMissingBean
    public ICrumbIssuerService crumbIssuerService(@Autowired(required = true) JenkinsClient jenkinsClient) {
        return new CrumbIssuerService(jenkinsClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public IJobService jobService(@Autowired(required = true) JenkinsClient jenkinsClient) {
        return new JobService(jenkinsClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public IQueueService queueService(@Autowired(required = true) JenkinsClient jenkinsClient) {
        return new QueueService(jenkinsClient);
    }

    @Bean
    @ConditionalOnMissingBean(name = "crumbSchedule")
    public CrumbSchedule crumbSchedule(JenkinsProperties properties,
                                       //
                                       ICrumbIssuerService crumbIssuerService) {
        return new CrumbSchedule(properties, crumbIssuerService);
    }

    @Bean
    @ConditionalOnMissingBean
    public JenkinsFaceService jenkinsFaceService(JenkinsProperties jenkinsProperties,
                                                 //
                                                 ICrumbIssuerService crumbIssuerService,
                                                 //
                                                 IJobService jobService,
                                                 //
                                                 IQueueService queueService) {
        return new JenkinsFaceService(jenkinsProperties, crumbIssuerService, jobService, queueService);
    }
}
