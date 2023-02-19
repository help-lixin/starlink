package help.lixin.core.definition.impl;

import help.lixin.core.plane.Plane;
import help.lixin.core.plane.impl.ElementShape;

/**
 * 组件定义
 */
public class PluginDefinition extends AbstractElementDefinition {
    protected Plane plane = new ElementShape();
    protected Boolean sync;

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
        return PluginDefinition.class.getName();
    }

    public Boolean getSync() {
        return sync;
    }

    public void setSync(Boolean sync) {
        this.sync = sync;
    }
}
