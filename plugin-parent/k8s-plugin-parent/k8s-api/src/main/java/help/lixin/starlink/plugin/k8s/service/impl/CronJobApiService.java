package help.lixin.starlink.plugin.k8s.service.impl;

import help.lixin.starlink.plugin.k8s.service.ICronJobApiService;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.batch.v1.CronJob;
import io.fabric8.kubernetes.api.model.batch.v1.CronJobBuilder;
import io.fabric8.kubernetes.api.model.batch.v1.CronJobList;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * @Author: 伍岳林
 * @Date: 2024/4/2 下午2:29
 * @Description
 */
public class CronJobApiService implements ICronJobApiService {

    private KubernetesClient client;

    public CronJobApiService(KubernetesClient client) {
        this.client = client;
    }

    @Override
    public CronJob createCronJob(CronJob cronJob) {
        return client.batch().v1().cronjobs().inNamespace(cronJob.getMetadata().getNamespace()).resource(cronJob)
            .createOrReplace();
    }

    @Override
    public CronJobList queryCronJobs(String namespace) {
        return client.batch().v1().cronjobs().inNamespace(namespace).list();
    }

    @Override
    public CronJob queryCronJob(String namespace, String jobName) {
        return client.batch().v1().cronjobs().inNamespace(namespace).withName(jobName).get();
    }

    @Override
    public Boolean pause(String namespace, String jobName) {
        CronJob cronJob = queryCronJob(namespace, jobName);
        cronJob.getSpec().setSuspend(true);
        return updateCronJob(cronJob) != null;
    }

    @Override
    public Boolean start(String namespace, String jobName) {
        CronJob cronJob = queryCronJob(namespace, jobName);
        deleteCronJob(namespace, jobName);
        cronJob.getSpec().setSuspend(false);
        return createCronJob(cronJob) != null;
    }

    @Override
    public CronJob updateCronJob(CronJob cronJob) {
        ObjectMeta metadata = cronJob.getMetadata();
        return client.batch().v1().cronjobs().inNamespace(metadata.getNamespace()).withName(metadata.getName())
            .edit(c -> new CronJobBuilder(cronJob).build());
    }

    @Override
    public boolean deleteCronJob(String namespace, String jobName) {
        return client.batch().v1().cronjobs().inNamespace(namespace).withName(jobName).delete().size() == 1;
    }
}
