package help.lixin.core.definition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 流水线定义
 */
public class PipelineDefinition implements Serializable {
    // 唯一的key
    private String key;
    // 流水性描述
    private String name;
    // 流水线链
    private List<ElementDefinition> pipelines = new ArrayList<ElementDefinition>(0);
    private Map<String, Object> others = new HashMap<>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getOthers() {
        return others;
    }

    public void setOthers(Map<String, Object> others) {
        this.others = others;
    }

    public List<ElementDefinition> getPipelines() {
        return pipelines;
    }

    public void addPipeline(ElementDefinition elementDefinition) {
        if (null != elementDefinition) {
            this.pipelines.add(elementDefinition);
        }
    }

    public void setPipelines(List<ElementDefinition> pipelines) {
        if (null != pipelines) {
            this.pipelines = pipelines;
        }
    }

    public void add(ElementDefinition pipeline) {
        if (null != pipelines) {
            pipelines.add(pipeline);
        }
    }
}
