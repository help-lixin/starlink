package help.lixin.starlink.plugin.jenkins.api.service;


import help.lixin.starlink.plugin.jenkins.api.properties.JenkinsProperties;

public class JenkinsFaceService {
    private ICrumbIssuerService crumbIssuerService;

    private IManageToolsConfigureService manageConfigureApiService;
    private IJobService jobService;
    private IQueueService queueService;
    private JenkinsProperties jenkinsProperties;
    private IKeyManagerService keyManagerService;


//    private JenkinsTemplateLoadFaceService jenkinsTemplateLoadFaceService;
//
//    private DownloadStrategyService downloadStrategyService;

    public JenkinsFaceService(JenkinsProperties jenkinsProperties,
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
                              //,
                              //
//                              JenkinsTemplateLoadFaceService jenkinsTemplateLoadFaceService,
                              //
//                              DownloadStrategyService downloadStrategyService
    ) {
        this.jenkinsProperties = jenkinsProperties;
        this.manageConfigureApiService = manageConfigureApiService;
        this.keyManagerService = keyManagerService;
        this.crumbIssuerService = crumbIssuerService;
        this.jobService = jobService;
        this.queueService = queueService;
//        this.jenkinsTemplateLoadFaceService = jenkinsTemplateLoadFaceService;
//        this.downloadStrategyService = downloadStrategyService;
    }

    public ICrumbIssuerService getCrumbIssuerService() {
        return crumbIssuerService;
    }

    public IJobService getJobService() {
        return jobService;
    }

    public IQueueService getQueueService() {
        return queueService;
    }

    public JenkinsProperties getJenkinsProperties() {
        return jenkinsProperties;
    }

//    public JenkinsTemplateLoadFaceService getJenkinsTemplateLoadFaceService() {
//        return jenkinsTemplateLoadFaceService;
//    }
//
//    public DownloadStrategyService getDownloadStrategyService() {
//        return downloadStrategyService;
//    }

    public IKeyManagerService getKeyManagerService() {
        return keyManagerService;
    }

    public IManageToolsConfigureService getManageConfigureApiService() {
        return manageConfigureApiService;
    }
}
