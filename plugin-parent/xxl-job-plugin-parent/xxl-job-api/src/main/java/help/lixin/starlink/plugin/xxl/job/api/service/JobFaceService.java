package help.lixin.starlink.plugin.xxl.job.api.service;


import help.lixin.starlink.plugin.xxl.job.api.properties.XxlJobProperties;

public class JobFaceService {
    private IJobGroupService jobGroupService;
    private IJobInfoService jobInfoService;
    private IJobLogService jobLogService;
    private ILoginService loginService;
    private XxlJobProperties xxlJobProperties;

    public JobFaceService(IJobGroupService jobGroupService, //
                          IJobInfoService jobInfoService, //
                          IJobLogService jobLogService, //
                          ILoginService loginService, //
                          XxlJobProperties xxlJobProperties) {
        this.xxlJobProperties = xxlJobProperties;
        this.jobGroupService = jobGroupService;
        this.jobInfoService = jobInfoService;
        this.jobLogService = jobLogService;
        this.loginService = loginService;
    }

    public IJobGroupService getJobGroupService() {
        return jobGroupService;
    }

    public void setJobGroupService(IJobGroupService jobGroupService) {
        this.jobGroupService = jobGroupService;
    }

    public IJobInfoService getJobInfoService() {
        return jobInfoService;
    }

    public void setJobInfoService(IJobInfoService jobInfoService) {
        this.jobInfoService = jobInfoService;
    }

    public IJobLogService getJobLogService() {
        return jobLogService;
    }

    public void setJobLogService(IJobLogService jobLogService) {
        this.jobLogService = jobLogService;
    }

    public ILoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(ILoginService loginService) {
        this.loginService = loginService;
    }

    public XxlJobProperties getXxlJobProperties() {
        return xxlJobProperties;
    }

    public void setXxlJobProperties(XxlJobProperties xxlJobProperties) {
        this.xxlJobProperties = xxlJobProperties;
    }
}
