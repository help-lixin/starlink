package help.lixin.core.log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class LogEntry implements Serializable {
    private String instanceId;
    private String nodeId;
    private String actionPlugin;
    private String body;
    private Map<String, Object> others = new HashMap<>();

    public void reset() {
        this.instanceId = null;
        this.nodeId = null;
        this.actionPlugin = null;
        this.body = null;
        this.others.clear();
    }

    public String getInstanceId() {
        return instanceId;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getActionPlugin() {
        return actionPlugin;
    }

    public void setActionPlugin(String actionPlugin) {
        this.actionPlugin = actionPlugin;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Map<String, Object> getOthers() {
        return others;
    }

    public void setOthers(Map<String, Object> others) {
        this.others = others;
    }

    @Override
    public String toString() {
        return "LogEntry{" +
                "instanceId='" + instanceId + '\'' +
                ", nodeId='" + nodeId + '\'' +
                ", actionPlugin='" + actionPlugin + '\'' +
                ", body='" + body + '\'' +
                ", others=" + others +
                '}';
    }
}
