package help.lixin.shell.service;

public class ShellFaceService {

    private IExpressionService expressionService;

    public ShellFaceService(IExpressionService expressionService) {
        this.expressionService = expressionService;
    }

    public IExpressionService getExpressionService() {
        return expressionService;
    }
}
