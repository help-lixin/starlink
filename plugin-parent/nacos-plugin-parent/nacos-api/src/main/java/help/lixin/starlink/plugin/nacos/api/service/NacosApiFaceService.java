package help.lixin.starlink.plugin.nacos.api.service;


import help.lixin.service.IExpressionService;

import java.util.Map;

public class NacosApiFaceService {

    protected IExpressionService expressionService;
    private INacosConfigService configService;
    private INacosInstanceService instanceService;
    private INacosNameSpaceService nameSpaceService;
    private INacosServiceManageService serviceManageService;

    public NacosApiFaceService(IExpressionService expressionService, //
                               INacosConfigService configService, //
                               INacosInstanceService instanceService, //
                               INacosNameSpaceService nameSpaceService, //
                               INacosServiceManageService serviceManageService) {
        this.expressionService = expressionService;
        this.configService = configService;
        this.instanceService = instanceService;
        this.nameSpaceService = nameSpaceService;
        this.serviceManageService = serviceManageService;
    }

    public INacosConfigService getConfigService() {
        return configService;
    }

    public void setConfigService(INacosConfigService configService) {
        this.configService = configService;
    }

    public INacosInstanceService getInstanceService() {
        return instanceService;
    }

    public void setInstanceService(INacosInstanceService instanceService) {
        this.instanceService = instanceService;
    }

    public INacosNameSpaceService getNameSpaceService() {
        return nameSpaceService;
    }

    public void setNameSpaceService(INacosNameSpaceService nameSpaceService) {
        this.nameSpaceService = nameSpaceService;
    }

    public INacosServiceManageService getServiceManageService() {
        return serviceManageService;
    }

    public void setServiceManageService(INacosServiceManageService serviceManageService) {
        this.serviceManageService = serviceManageService;
    }

    public String expression(String template, Map<String, Object> ctx) {
        return expressionService.prase(template, ctx);
    }
}
