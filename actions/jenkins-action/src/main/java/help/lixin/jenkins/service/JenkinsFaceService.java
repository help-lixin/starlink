package help.lixin.jenkins.service;


public class JenkinsFaceService {
    private ICrumbIssuerService crumbIssuerService;
    private IJobService jobService;
    private IQueueService queueService;

    public JenkinsFaceService(ICrumbIssuerService crumbIssuerService,
                              //
                              IJobService jobService,
                              //
                              IQueueService queueService) {
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
}
