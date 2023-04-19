package help.lixin.jenkins.service;


import help.lixin.jenkins.properties.JenkinsProperties;

public class JenkinsFaceService {
    private ICrumbIssuerService crumbIssuerService;
    private IJobService jobService;
    private IQueueService queueService;
    private JenkinsProperties jenkinsProperties;

    private JenkinsTemplateLoadFaceService jenkinsTemplateLoadFaceService;

    private DownloadStrategyService downloadStrategyService;

    public JenkinsFaceService(JenkinsProperties jenkinsProperties,
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
        this.jenkinsProperties = jenkinsProperties;
        this.crumbIssuerService = crumbIssuerService;
        this.jobService = jobService;
        this.queueService = queueService;
        this.jenkinsTemplateLoadFaceService = jenkinsTemplateLoadFaceService;
        this.downloadStrategyService = downloadStrategyService;
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

    public JenkinsTemplateLoadFaceService getJenkinsTemplateLoadFaceService() {
        return jenkinsTemplateLoadFaceService;
    }

    public DownloadStrategyService getDownloadStrategyService() {
        return downloadStrategyService;
    }
}
