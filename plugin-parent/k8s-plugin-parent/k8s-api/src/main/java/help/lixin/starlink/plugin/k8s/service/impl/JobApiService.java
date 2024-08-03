package help.lixin.starlink.plugin.k8s.service.impl;

import help.lixin.starlink.plugin.k8s.service.IJobApiService;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.api.model.batch.v1.Job;
import io.fabric8.kubernetes.api.model.batch.v1.JobBuilder;
import io.fabric8.kubernetes.api.model.batch.v1.JobList;
import io.fabric8.kubernetes.client.KubernetesClient;

/**
 * @Author: 伍岳林
 * @Date: 2024/4/2 上午10:09
 * @Description
 */
public class JobApiService implements IJobApiService {

    private KubernetesClient client;

    public JobApiService(KubernetesClient client) {
        this.client = client;
    }

    @Override
    public Job createJob(Job job) {
        ObjectMeta metadata = job.getMetadata();
        return client.batch().v1().jobs().inNamespace(metadata.getNamespace()).resource(job).createOrReplace();
    }

    @Override
    public JobList queryJobs(String namespace) {
        return client.batch().v1().jobs().inNamespace(namespace).list();
    }

    @Override
    public Job queryJob(String namespace, String jobName) {
        return client.batch().v1().jobs().inNamespace(namespace).withName(jobName).get();
    }

    @Override
    public Boolean restart(String namespace, String jobName) {
        Job job = queryJob(namespace, jobName);
        delete(namespace, jobName);
        job.getMetadata().getLabels().remove("controller-uid");
        job.getSpec().getSelector().getMatchLabels().remove("controller-uid");
        job.getSpec().getTemplate().getMetadata().getLabels().remove("controller-uid");
        return createJob(job) != null;
    }

    @Override
    public Job updateJob(Job job) {
        ObjectMeta metadata = job.getMetadata();
        return client.batch().v1().jobs().inNamespace(metadata.getNamespace()).withName(metadata.getName())
            .edit(j -> new JobBuilder(job).build());
    }

    @Override
    public boolean delete(String namespace, String jobName) {
        return client.batch().v1().jobs().inNamespace(namespace).withName(jobName).delete().size() == 1;
    }
}
