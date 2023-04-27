package help.lixin.jib.service;

import help.lixin.core.pipeline.service.IExpressionService;

public class JibFaceService {

    private IExpressionService expressionService;

    public IExpressionService getExpressionService() {
        return expressionService;
    }

    public void setExpressionService(IExpressionService expressionService) {
        this.expressionService = expressionService;
    }
}
