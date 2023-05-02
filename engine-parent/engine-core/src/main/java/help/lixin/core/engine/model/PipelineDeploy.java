package help.lixin.core.engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import help.lixin.core.definition.PipelineDefinition;

public class PipelineDeploy {
    private String id;
    private String name;
    private String key;
    private int version;
    @JsonIgnore
    private PipelineDefinition pipelineDefinition;

    public PipelineDefinition getPipelineDefinition() {
        return pipelineDefinition;
    }

    public void setPipelineDefinition(PipelineDefinition pipelineDefinition) {
        this.pipelineDefinition = pipelineDefinition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "PipelineDeploy{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", key='" + key + '\'' +
                ", version=" + version +
                '}';
    }
}
