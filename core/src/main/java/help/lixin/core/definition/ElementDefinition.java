package help.lixin.core.definition;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import help.lixin.core.definition.impl.PluginDefinition;
import help.lixin.core.definition.impl.SequenceFlowDefinition;
import help.lixin.core.plane.Plane;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "clazz")
@JsonSubTypes(
        {@JsonSubTypes.Type(value = PluginDefinition.class, name = "help.lixin.core.definition.impl.PluginDefinition"),
         @JsonSubTypes.Type(value = SequenceFlowDefinition.class, name = "help.lixin.core.definition.impl.SequenceFlowDefinition")}
)
public interface ElementDefinition {
    void setId(String id);

    String getId();

    void setName(String name);

    String getName();

    void setSource(String source);

    String getSource();

    void setTarget(String target);

    String getTarget();

    void setParams(String params);

    String getParams();

    void setPlane(Plane plane);

    Plane getPlane();

    String getPlugin();

    void setPlugin(String plugin);
}
