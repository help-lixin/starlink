package help.lixin.starlink.plugin.k8s.mq.constants;

import help.lixin.starlink.plugin.k8s.mq.event.K8sSyncPodEvent;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/25 下午1:58
 * @Description
 */
public class K8sConstant {
    public static final String SYNC_PODS_EVENT_TOPIC = K8sSyncPodEvent.class.getName().replaceAll("\\.", "_");

    public static final String CRON_JOB = "CronJob";
    public static final String DEPLOYMENT = "Deployment";
    public static final String JOB = "Job";
    public static final String SERVICE = "Service";
    public static final String DAEMONSET = "DaemonSet";
}
