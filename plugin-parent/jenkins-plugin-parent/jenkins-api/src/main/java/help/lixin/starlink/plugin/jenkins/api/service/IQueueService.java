package help.lixin.starlink.plugin.jenkins.api.service;

import com.cdancy.jenkins.rest.domain.common.RequestStatus;
import com.cdancy.jenkins.rest.domain.queue.QueueItem;

import java.util.List;

public interface IQueueService {
    /**
     * 列出所有的队列
     *
     * @return
     */
    List<QueueItem> all();

    /**
     * 根据队列id,获得队列信息
     *
     * @param queueId
     * @return
     */
    QueueItem queueItem(int queueId);

    /**
     * 取消队列里的任务
     *
     * @param id
     * @return
     */
    RequestStatus cancel(int id);
}
