package help.lixin.jenkins.service;


import help.lixin.jenkins.properties.JenkinsProperties;

public class JenkinsFaceService {
    private ICrumbIssuerService crumbIssuerService;
    private IJobService jobService;
    private IQueueService queueService;
    private JenkinsProperties jenkinsProperties;

    public JenkinsFaceService(JenkinsProperties jenkinsProperties,
                              //
                              ICrumbIssuerService crumbIssuerService,
                              //
                              IJobService jobService,
                              //
                              IQueueService queueService) {
        this.jenkinsProperties = jenkinsProperties;
        this.crumbIssuerService = crumbIssuerService;
        this.jobService = jobService;
        this.queueService = queueService;
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
}
