package help.lixin.svn.service;

import help.lixin.core.pipeline.service.IExpressionService;

public class SvnFaceService {
    private IExpressionService expressionService;

    public void setExpressionService(IExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    public IExpressionService getExpressionService() {
        return expressionService;
    }
}
