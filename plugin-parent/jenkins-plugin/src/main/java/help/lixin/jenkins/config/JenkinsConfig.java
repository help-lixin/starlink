package help.lixin.jenkins.config;

import com.cdancy.jenkins.rest.JenkinsClient;
import help.lixin.jenkins.action.entity.DownloadStrategy;
import help.lixin.jenkins.properties.JenkinsProperties;
import help.lixin.jenkins.schedule.CrumbSchedule;
import help.lixin.jenkins.service.*;
import help.lixin.jenkins.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                                                 IQueueService queueService,
                                                 //
                                                 JenkinsTemplateLoadFaceService jenkinsTemplateLoadFaceService,
                                                 //
                                                 DownloadStrategyService downloadStrategyService) {
        return new JenkinsFaceService(jenkinsProperties, crumbIssuerService, jobService, queueService, jenkinsTemplateLoadFaceService, downloadStrategyService);
    }

    @Bean
    public JenkinsTemplateLoadFaceService jenkinsTemplateLoadFaceService(ILoadJenkinsTemplateService loadJenkinsTemplateService) {
        JenkinsTemplateLoadFaceService faceService = new JenkinsTemplateLoadFaceService(loadJenkinsTemplateService);
        return faceService;
    }


    @Bean
    @Primary
    @ConditionalOnMissingBean(name = "databaseLoadJenkinsTemplateService")
    public ILoadJenkinsTemplateService databaseLoadJenkinsTemplateService(@Autowired(required = false) @Qualifier("diskLoadJenkinsTemplateService") ILoadJenkinsTemplateService diskLoadJenkinsTemplateService) {
        DatabaseLoadJenkinsTemplateService databaseLoadJenkinsTemplateService = new DatabaseLoadJenkinsTemplateService();
        if (null != diskLoadJenkinsTemplateService) {
            databaseLoadJenkinsTemplateService.setParent(diskLoadJenkinsTemplateService);
        }
        return databaseLoadJenkinsTemplateService;
    }

    @Bean
    @ConditionalOnMissingBean(name = "diskLoadJenkinsTemplateService")
    public ILoadJenkinsTemplateService diskLoadJenkinsTemplateService() {
        DiskLoadJenkinsTemplateService diskLoadJenkinsTemplateService = new DiskLoadJenkinsTemplateService();
        diskLoadJenkinsTemplateService.setParent(null);
        return diskLoadJenkinsTemplateService;
    }

    @Bean
    @ConditionalOnMissingBean(name = "remoteDownloadArtifactService")
    public IDownloadArtifactService remoteDownloadArtifactService(IJobService jobService) {
        RemoteDownloadArtifactService remoteDownloadArtifactService = new RemoteDownloadArtifactService(jobService);
        return remoteDownloadArtifactService;
    }

    @Bean
    @ConditionalOnMissingBean(name = "downloadStrategyService")
    public DownloadStrategyService downloadStrategyService(@Autowired(required = false) List<IDownloadArtifactService> downloadArtifactServices) {
        Map<DownloadStrategy, IDownloadArtifactService> downloadArtifactServiceMap = new HashMap<>();
        if (null != downloadArtifactServices) {
            for (IDownloadArtifactService downloadArtifactService : downloadArtifactServices) {
                downloadArtifactServiceMap.put(downloadArtifactService.support(), downloadArtifactService);
            }
        }

        DownloadStrategyService downloadStrategyService = new DownloadStrategyService(downloadArtifactServiceMap);
        return downloadStrategyService;
    }

}
