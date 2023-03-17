package help.lixin.core.engine.service;

import org.camunda.bpm.client.ExternalTaskClientBuilder;

public interface IExternalTaskClientCustomizer {
    void customizer(ExternalTaskClientBuilder externalTaskClientBuilder);
}
