package help.lixin.core.definition;

import help.lixin.core.meta.Plane;

public interface
ElementDefinition {
    void setId(String id);

    String getId();

    void setName(String name);

    String getName();

    void setSource(String source);

    String getSource();

    void setTarget(String target);

    String getTarget();

    void setPlane(Plane plane);

    Plane getPlane();
}
