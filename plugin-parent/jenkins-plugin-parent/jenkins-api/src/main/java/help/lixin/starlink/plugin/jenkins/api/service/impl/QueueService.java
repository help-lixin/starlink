package help.lixin.starlink.plugin.jenkins.api.service.impl;

import com.cdancy.jenkins.rest.JenkinsClient;
import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.queue.QueueItem;
import com.cdancy.jenkins.rest.features.QueueApi;
import help.lixin.starlink.plugin.jenkins.api.service.IQueueService;

import java.util.List;

public class QueueService implements IQueueService {
    private JenkinsClient client;

    public QueueService(JenkinsClient client) {
        this.client = client;
    }

    @Override
    public List<QueueItem> all() {
        QueueApi queueApi = client.api().queueApi();
        return queueApi.queue();
    }

    @Override
    public QueueItem queueItem(int queueId) {
        QueueApi queueApi = client.api().queueApi();
        return queueApi.queueItem(queueId);
    }

    @Override
    public RequestStatus cancel(int id) {
        QueueApi queueApi = client.api().queueApi();
        return queueApi.cancel(id);
    }
}
