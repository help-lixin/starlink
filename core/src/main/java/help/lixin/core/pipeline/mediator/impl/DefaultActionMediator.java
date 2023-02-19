package help.lixin.core.pipeline.mediator.impl;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.mediator.ActionMediator;

import java.util.concurrent.ConcurrentHashMap;

public class DefaultActionMediator implements ActionMediator {

    private static final ConcurrentHashMap<String, Action> ACTIONS = new ConcurrentHashMap<>();

    @Override
    public void register(String actionName, Action action) {
        ACTIONS.put(actionName, action);
    }

    @Override
    public void unRegister(String actionName) {
        ACTIONS.remove(actionName);
    }

    @Override
    public Action getAction(String actionName) {
        return ACTIONS.get(actionName);
    }
}
