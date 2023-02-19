package help.lixin.core.definition.impl;

import help.lixin.core.meta.Plane;
import help.lixin.core.meta.impl.ElementShape;

/**
 * 组件定义
 */
public class ComponentDefinition extends AbstractElementDefinition {
    protected Plane plane = new ElementShape();

    @Override
    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    @Override
    public Plane getPlane() {
        return plane;
    }

    @Override
    public String getClazz() {
        return ComponentDefinition.class.getName();
    }
}
