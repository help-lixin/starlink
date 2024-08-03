package help.lixin.starlink.plugin.rocketmq.api.dto.topic;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/30 4:56 下午
 * @Description
 */
public class TopicGroupInfoDTO {

    private Boolean status;

    private String errMsg;

    private List<TopicRollBackInfoDTO> rollbackStatsList;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<TopicRollBackInfoDTO> getRollbackStatsList() {
        return rollbackStatsList;
    }

    public void setRollbackStatsList(List<TopicRollBackInfoDTO> rollbackStatsList) {
        this.rollbackStatsList = rollbackStatsList;
    }
}
