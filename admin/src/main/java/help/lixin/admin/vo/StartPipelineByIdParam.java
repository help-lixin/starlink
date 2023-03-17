package help.lixin.admin.vo;

public class StartPipelineByIdParam extends AbstractStartPipelineParam {
    private String deployId;

    public String getDeployId() {
        return deployId;
    }

    public void setDeployId(String deployId) {
        this.deployId = deployId;
    }

    @Override
    public String toString() {
        return "StartPipelineByIdParam{" +
                "deployId='" + deployId + '\'' +
                '}';
    }
}
