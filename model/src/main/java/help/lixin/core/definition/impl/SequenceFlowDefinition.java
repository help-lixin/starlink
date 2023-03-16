package help.lixin.core.definition.impl;

import help.lixin.core.plane.Plane;
import help.lixin.core.plane.impl.ElementEdge;

/**
 * 泳道
 */
public class SequenceFlowDefinition extends AbstractElementDefinition {

    public static final String SEQUENCE_FLOW_ACTION = "flow";

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
    public String getPlugin() {
        String plugin = super.getPlugin();
        if (null == plugin) {
            return SEQUENCE_FLOW_ACTION;
        }
        return plugin;
    }

    @Override
    public String getClazz() {
        return SequenceFlowDefinition.class.getName();
    }
}
