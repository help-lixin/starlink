package help.lixin.starlink.plugin.k8s.service;

import io.fabric8.kubernetes.api.model.batch.v1.Job;
import io.fabric8.kubernetes.api.model.batch.v1.JobList;

public interface IJobApiService {

    Job createJob(Job job);

    JobList queryJobs(String namespace);

    Job queryJob(String namespace, String jobName);

    Boolean restart(String namespace, String jobName);

    Job updateJob(Job job);

    boolean delete(String namespace, String jobName);

}
