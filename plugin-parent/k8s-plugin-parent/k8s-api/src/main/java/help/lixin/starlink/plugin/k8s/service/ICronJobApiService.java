package help.lixin.starlink.plugin.k8s.service;

import io.fabric8.kubernetes.api.model.batch.v1.CronJob;
import io.fabric8.kubernetes.api.model.batch.v1.CronJobList;

public interface ICronJobApiService {

    CronJob createCronJob(CronJob cronJob);

    CronJobList queryCronJobs(String namespace);

    CronJob queryCronJob(String namespace, String jobName);

    Boolean pause(String namespace, String jobName);

    Boolean start(String namespace, String jobName);

    CronJob updateCronJob(CronJob cronJob);

    boolean deleteCronJob(String namespace, String jobName);

}
