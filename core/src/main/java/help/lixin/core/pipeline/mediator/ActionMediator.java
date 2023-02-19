package help.lixin.core.pipeline.mediator;

import help.lixin.core.pipeline.action.Action;

public interface ActionMediator {

    void register(String actionName, Action action);

    void unRegister(String actionName);

    Action getAction(String actionName);
}
