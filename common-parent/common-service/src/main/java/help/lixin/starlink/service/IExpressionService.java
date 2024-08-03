package help.lixin.service;

import java.util.Map;


public interface IExpressionService {

    void setContextCustomizer(IContextCustomizer contextCustomizer);

    IContextCustomizer getContextCustomizer();

    String prase(String expressionString, Map<String, Object> context);
}
