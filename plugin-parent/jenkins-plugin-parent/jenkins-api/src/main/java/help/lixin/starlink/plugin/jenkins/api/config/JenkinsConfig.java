package help.lixin.starlink.plugin.jenkins.api.config;

import com.cdancy.jenkins.rest.JenkinsClient;
import help.lixin.starlink.plugin.jenkins.api.properties.JenkinsProperties;
import help.lixin.starlink.plugin.jenkins.api.service.*;
import help.lixin.starlink.plugin.jenkins.api.service.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

public class JenkinsConfig {

    @Value("${plugin:default}")
    private String plugin;

    @Value("${instance:default}")
    private String instance;

    @Bean
    public JenkinsProperties jenkinsProperties(Environment environment) {
        String prefix = String.format("%s.%s", plugin, instance);
        JenkinsProperties jenkinsProperties = Binder.get(environment)//
                .bind(prefix, JenkinsProperties.class)//
                .get();
        return jenkinsProperties;
    }

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
    @ConditionalOnMissingBean
    public IPluginService pluginService(@Autowired(required = true) JenkinsClient jenkinsClient, //
                                        ICrumbIssuerService crumbIssuerService) {
        return new PluginService(crumbIssuerService, jenkinsClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public ISafeRestartService safeRestartService(@Autowired(required = true) JenkinsClient jenkinsClient, //
                                                  ICrumbIssuerService crumbIssuerService) {
        return new SafeRestartService(crumbIssuerService, jenkinsClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public IUpdateSiteService updateSiteService(@Autowired(required = true) JenkinsClient jenkinsClient, //
                                                ICrumbIssuerService crumbIssuerService) {
        return new UpdateSiteService(crumbIssuerService, jenkinsClient);
    }

    @Bean
    @ConditionalOnMissingBean
    public JenkinsFaceService jenkinsFaceService(JenkinsProperties jenkinsProperties,
                                                 //
                                                 IManageToolsConfigureService manageConfigureApiService,
                                                 //
                                                 IKeyManagerService keyManagerService,
                                                 //
                                                 ICrumbIssuerService crumbIssuerService,
                                                 //
                                                 IJobService jobService,
                                                 //
                                                 IQueueService queueService
                                                 //
                                                 // JenkinsTemplateLoadFaceService jenkinsTemplateLoadFaceService,
                                                 //
                                                 // DownloadStrategyService downloadStrategyService
    ) {
//        return new JenkinsFaceService(jenkinsProperties, manageConfigureApiService, keyManagerService,
//                crumbIssuerService, jobService, queueService, jenkinsTemplateLoadFaceService, downloadStrategyService);

        return new JenkinsFaceService(jenkinsProperties, manageConfigureApiService, keyManagerService,
                crumbIssuerService, jobService, queueService);
    }


//    @Bean
//    public JenkinsTemplateLoadFaceService jenkinsTemplateLoadFaceService(ILoadJenkinsTemplateService loadJenkinsTemplateService) {
//        JenkinsTemplateLoadFaceService faceService = new JenkinsTemplateLoadFaceService(loadJenkinsTemplateService);
//        return faceService;
//    }
//
//
//    @Bean
//    @Primary
//    @ConditionalOnMissingBean(name = "databaseLoadJenkinsTemplateService")
//    public ILoadJenkinsTemplateService databaseLoadJenkinsTemplateService(@Autowired(required = false) @Qualifier("diskLoadJenkinsTemplateService") ILoadJenkinsTemplateService diskLoadJenkinsTemplateService) {
//        DatabaseLoadJenkinsTemplateService databaseLoadJenkinsTemplateService = new DatabaseLoadJenkinsTemplateService();
//        if (null != diskLoadJenkinsTemplateService) {
//            databaseLoadJenkinsTemplateService.setParent(diskLoadJenkinsTemplateService);
//        }
//        return databaseLoadJenkinsTemplateService;
//    }
//
//    @Bean
//    @ConditionalOnMissingBean(name = "diskLoadJenkinsTemplateService")
//    public ILoadJenkinsTemplateService diskLoadJenkinsTemplateService() {
//        DiskLoadJenkinsTemplateService diskLoadJenkinsTemplateService = new DiskLoadJenkinsTemplateService();
//        diskLoadJenkinsTemplateService.setParent(null);
//        return diskLoadJenkinsTemplateService;
//    }

//    @Bean
//    @ConditionalOnMissingBean(name = "remoteDownloadArtifactService")
//    public IDownloadArtifactService remoteDownloadArtifactService(IJobService jobService) {
//        RemoteDownloadArtifactService remoteDownloadArtifactService = new RemoteDownloadArtifactService(jobService);
//        return remoteDownloadArtifactService;
//    }

//    @Bean
//    @ConditionalOnMissingBean(name = "downloadStrategyService")
//    public DownloadStrategyService downloadStrategyService(@Autowired(required = false) List<IDownloadArtifactService> downloadArtifactServices) {
//        Map<DownloadStrategy, IDownloadArtifactService> downloadArtifactServiceMap = new HashMap<>();
//        if (null != downloadArtifactServices) {
//            for (IDownloadArtifactService downloadArtifactService : downloadArtifactServices) {
//                downloadArtifactServiceMap.put(downloadArtifactService.support(), downloadArtifactService);
//            }
//        }
//
//        DownloadStrategyService downloadStrategyService = new DownloadStrategyService(downloadArtifactServiceMap);
//        return downloadStrategyService;
//    }

    @Bean
    @ConditionalOnMissingBean(name = "keyManagerService")
    public IKeyManagerService keyManagerService(ICrumbIssuerService crumbIssuerService,
                                                JenkinsClient jenkinsClient) {
        return new KeyManagerService(crumbIssuerService, jenkinsClient);
    }

    @Bean
    @ConditionalOnMissingBean(name = "manageConfigureApiService")
    public IManageToolsConfigureService manageConfigureApiService(ICrumbIssuerService crumbIssuerService,
                                                                  //
                                                                  JenkinsClient jenkinsClient) {
        return new ManageToolsConfigureService(crumbIssuerService, jenkinsClient);
    }
}
