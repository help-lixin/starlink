package help.lixin.core.pipeline.action.impl;

import help.lixin.core.definition.impl.SequenceFlowDefinition;
import help.lixin.core.pipeline.action.Action;

public class SequenceFlowAction implements Action {

    @Override
    public String name() {
        return SequenceFlowDefinition.SEQUENCE_FLOW_ACTION;
    }
}
