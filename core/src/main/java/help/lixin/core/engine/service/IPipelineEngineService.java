package help.lixin.core.engine.service;

/**
 * Pipeline 引擎管理(实际只是一个Face对象)
 */
public interface IPipelineEngineService {

    IPipelineRuntimeService getPipelineInstanceService();

    IPipelineLogService getPipelineLogService();

    IPipelineDeployService getPipelineDeployService();
}
