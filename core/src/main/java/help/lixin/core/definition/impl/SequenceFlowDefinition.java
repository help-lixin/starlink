package help.lixin.core.definition.impl;

import help.lixin.core.meta.Plane;
import help.lixin.core.meta.impl.ElementEdge;

/**
 * 线
 */
public class SequenceFlowDefinition extends AbstractElementDefinition {

    protected Plane plane = new ElementEdge();


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
        return SequenceFlowDefinition.class.getName();
    }
}
