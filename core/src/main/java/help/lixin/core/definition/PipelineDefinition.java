package help.lixin.core.definition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流水线定义
 */
public class PipelineDefinition {
    private String name;
    private List<ElementDefinition> pipelines = new ArrayList<>();
    private Map<String, Object> others = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ElementDefinition> getPipelines() {
        return pipelines;
    }

    public void setPipelines(List<ElementDefinition> pipelines) {
        if (null != pipelines) {
            this.pipelines = pipelines;
        }
    }

    public Map<String, Object> getOthers() {
        return others;
    }

    public void setOthers(Map<String, Object> others) {
        this.others = others;
    }

    public void add(ElementDefinition pipeline) {
        if (null != pipelines) {
            pipelines.add(pipeline);
        }
    }
}
