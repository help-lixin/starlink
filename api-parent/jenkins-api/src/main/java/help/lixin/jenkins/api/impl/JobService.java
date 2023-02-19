package help.lixin.jenkins.api.impl;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.common.IntegerResponse;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.job.BuildInfo;
import com.cdancy.jenkins.rest.domain.job.JobInfo;
import com.cdancy.jenkins.rest.domain.job.JobList;
import com.cdancy.jenkins.rest.domain.job.ProgressiveText;
import com.cdancy.jenkins.rest.features.JobsApi;
import help.lixin.jenkins.api.IJobService;
import help.lixin.jenkins.model.CreateJobContext;
import help.lixin.jenkins.model.TriggerBuildContext;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class JobService implements IJobService {
    private JenkinsClient client;

    public JobService(JenkinsClient client) {
        this.client = client;
    }

    @Override
    public JobList query(String folderPath) {
        JobsApi jobsApi = client.api().jobsApi();
        if (null == folderPath) {
            folderPath = "";
        }
        JobList restList = jobsApi.jobList(folderPath);
        return restList;
    }

    @Override
    public RequestStatus createJob(CreateJobContext context) {
        JobsApi jobsApi = client.api().jobsApi();
        RequestStatus requestStatus = jobsApi.create(context.getOptionalFolderPath(),
                //
                context.getJobName(),
                //
                context.getConfigXML());
        return requestStatus;
    }

    @Override
    public IntegerResponse triggerBuild(TriggerBuildContext ctx) {
        JobsApi jobsApi = client.api().jobsApi();

        Map<String, List<String>> properties = ctx.getProperties();
        if (properties.size() > 0) { // 有参数和无参数是要分开agi来着
            IntegerResponse response = jobsApi.buildWithParameters(
                    //
                    ctx.getOptionalFolderPath(),
                    //
                    ctx.getJobName(),
                    //
                    ctx.getProperties());
            return response;
        } else {
            IntegerResponse response = jobsApi.build(
                    //
                    ctx.getOptionalFolderPath(),
                    //
                    ctx.getJobName());
            return response;
        }
    }

    @Override
    public RequestStatus deleteJob(String optionalFolderPath, String jobName) {
        JobsApi jobsApi = client.api().jobsApi();
        RequestStatus status = jobsApi.delete(optionalFolderPath, jobName);
        return status;
    }

    @Override
    public boolean enable(String optionalFolderPath, String jobName) {
        JobsApi jobsApi = client.api().jobsApi();
        boolean enable = jobsApi.enable(optionalFolderPath, jobName);
        return enable;
    }

    @Override
    public boolean disable(String optionalFolderPath, String jobName) {
        JobsApi jobsApi = client.api().jobsApi();
        boolean disable = jobsApi.disable(optionalFolderPath, jobName);
        return disable;
    }

    @Override
    public RequestStatus term(String optionalFolderPath, String jobName, int buildNumber) {
        JobsApi jobsApi = client.api().jobsApi();
        RequestStatus status = jobsApi.term(optionalFolderPath, jobName, buildNumber);
        return status;
    }

    @Override
    public RequestStatus kill(String optionalFolderPath, String jobName, int buildNumber) {
        JobsApi jobsApi = client.api().jobsApi();
        RequestStatus status = jobsApi.kill(optionalFolderPath, jobName, buildNumber);
        return status;
    }

    @Override
    public String getConfigXml(String optionalFolderPath, String jobName) {
        JobsApi jobsApi = client.api().jobsApi();
        return jobsApi.config(optionalFolderPath, jobName);
    }

    @Override
    public JobInfo getJobInfo(String optionalFolderPath, String jobName) {
        JobsApi jobsApi = client.api().jobsApi();
        return jobsApi.jobInfo(optionalFolderPath, jobName);
    }

    @Override
    public BuildInfo getBuildInfo(String optionalFolderPath, String jobName, int buildNumber) {
        JobsApi jobsApi = client.api().jobsApi();
        return jobsApi.buildInfo(optionalFolderPath, jobName, buildNumber);
    }

    @Override
    public InputStream artifact(String optionalFolderPath, String jobName, int buildNumber, String relativeArtifactPath) {
        JobsApi jobsApi = client.api().jobsApi();
        InputStream artifact = jobsApi.artifact(optionalFolderPath, jobName, buildNumber, relativeArtifactPath);
        return artifact;
    }

    @Override
    public ProgressiveText lookBuildLog(String optionalFolderPath, String jobName, int buildNumber, int start) {
        JobsApi jobsApi = client.api().jobsApi();
        ProgressiveText progressiveText = jobsApi.progressiveText(optionalFolderPath, jobName, buildNumber, start);
        return progressiveText;
    }
}
