package help.lixin.core.pipeline.config;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.action.impl.SequenceFlowAction;
import help.lixin.core.pipeline.mediator.ActionMediator;
import help.lixin.core.pipeline.mediator.impl.DefaultActionMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ActionsConfig {
    // 定义默认的流
    @Bean
    @ConditionalOnMissingBean(name = "sequenceFlowAction")
    public Action sequenceFlowAction() {
        return new SequenceFlowAction();
    }

    @Bean
    @ConditionalOnMissingBean
    public ActionMediator actionMediator(@Autowired(required = false) List<Action> actions) {
        ActionMediator mediator = new DefaultActionMediator();
        if (null != actions && !actions.isEmpty()) {
            for (Action action : actions) {
                String name = action.name();
                Action registerAction = action;
                mediator.register(name, registerAction);
            }
        }
        return mediator;
    }
}
