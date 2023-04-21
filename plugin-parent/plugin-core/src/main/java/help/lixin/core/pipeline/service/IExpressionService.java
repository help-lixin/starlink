package help.lixin.core.pipeline.service;

import java.util.Map;

public interface IExpressionService {
    void setContextCustomizer(IContextCustomizer contextCustomizer);

    IContextCustomizer getContextCustomizer();

    String prase(String template, Map<String, Object> context);
}
