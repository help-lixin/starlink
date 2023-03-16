package help.lixin.core.engine.model;

public class PipelineInstance {
    private String id;
    private String pipelineDeployId;
    private String businessKey;
    private String rootPipelineInstanceId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPipelineDeployId() {
        return pipelineDeployId;
    }

    public void setPipelineDeployId(String pipelineDeployId) {
        this.pipelineDeployId = pipelineDeployId;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getRootPipelineInstanceId() {
        return rootPipelineInstanceId;
    }

    public void setRootPipelineInstanceId(String rootPipelineInstanceId) {
        this.rootPipelineInstanceId = rootPipelineInstanceId;
    }

    @Override
    public String toString() {
        return "PipelineInstance{" + "id='" + id + '\'' + ", pipelineDeployId='" + pipelineDeployId + '\'' + ", businessKey='" + businessKey + '\'' + ", rootPipelineInstanceId='" + rootPipelineInstanceId + '\'' + '}';
    }
}
