package help.lixin.shell.service;

import java.util.Map;

public interface IExpressionService {
    void setContextCustomizer(IContextCustomizer contextCustomizer);

    IContextCustomizer getContextCustomizer();

    String prase(String template, Map<String, Object> context);
}
