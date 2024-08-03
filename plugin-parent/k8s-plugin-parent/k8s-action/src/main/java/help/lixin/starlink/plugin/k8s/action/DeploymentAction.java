package help.lixin.starlink.plugin.k8s.action;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.log.Log;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.k8s.action.entity.DeploymentParams;
import help.lixin.starlink.plugin.k8s.action.entity.DeploymentVar;
import help.lixin.starlink.plugin.k8s.domain.KubernetesDeployTemplate;
import help.lixin.starlink.plugin.k8s.service.IDeploymentApiService;
import help.lixin.starlink.plugin.k8s.service.IK8sDeployTemplateService;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DeploymentAction implements Action {
    private Logger logger = LoggerFactory.getLogger(DeploymentAction.class);
    public static final String K8S_DEPLOYMENT_ACTION = "k8s-deploy";
    public static final String IMAGE_PULL_SECRET_NAME = "IMAGE_PULL_SECRET_NAME";
    public static final String DEPLOY_NAME = "DEPLOY_NAME";

    private AbstractServiceFactory k8SServiceFactory;

    private IExpressionService expressionService;

    private IK8sDeployTemplateService k8sDeployTemplateService;

    public DeploymentAction(IExpressionService expressionService, //
                            AbstractServiceFactory k8SServiceFactory, //
                            IK8sDeployTemplateService k8sDeployTemplateService //
    ) {
        this.expressionService = expressionService;
        this.k8SServiceFactory = k8SServiceFactory;
        this.k8sDeployTemplateService = k8sDeployTemplateService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        // 1. 参数解析
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DeploymentParams params = mapper.readValue(stageParams, DeploymentParams.class);
        String instance = params.getInstanceCode();
        // 2. 根据凭证id,换取回来,相应的k8s凭证
        String k8SCredentialName = params.getCredentialName();

        // 3. 根据模板id,查询出模板信息
        Long deployTemplateId = params.getDeployTemplateId();
        if (null == deployTemplateId) {
            String msg = String.format("%s插件执行失败,deployTemplateId是必填项", K8S_DEPLOYMENT_ACTION);
            Log.trace(msg);
            throw new RuntimeException(msg);
        }
        KubernetesDeployTemplate kubernetesDeployTemplate = k8sDeployTemplateService.get(deployTemplateId);
        if (null == kubernetesDeployTemplate) {
            String msg = String.format("%s插件执行失败,根据deployTemplateId未获取到数据", K8S_DEPLOYMENT_ACTION);
            Log.trace(msg);
            throw new RuntimeException(msg);
        }

        // 获取到YAML内容
        String yaml = kubernetesDeployTemplate.getYamlContent();
        String deployName = kubernetesDeployTemplate.getDeployName();

        // 临时变量
        Map<String, Object> tmpCtx = new HashMap<>();
        tmpCtx.put(DEPLOY_NAME, deployName);
        tmpCtx.put(IMAGE_PULL_SECRET_NAME, k8SCredentialName);
        tmpCtx.putAll(ctx.getVars());
        tmpCtx.putAll(params.getVars().stream().collect(Collectors.toMap(DeploymentVar::getName, DeploymentVar::getValue)));
        yaml = expressionService.prase(yaml, tmpCtx);

        String lastYmlMsg = String.format("kubernetes准备应用yaml配置[\n%s\n]", yaml);
        Log.trace(lastYmlMsg);

        IDeploymentApiService deploymentApiService = k8SServiceFactory.getInstance(instance, IDeploymentApiService.class);
        // 把yaml转换成:Deployment对象
        Deployment deployment = deploymentApiService.yamlConvertDeployment(yaml);
        // 配置命名空间和部署名称
        deployment.getMetadata().setNamespace(params.getNamespace());
        if (null != tmpCtx.get(DEPLOY_NAME)) {
            deployment.getMetadata().setName((String) tmpCtx.get(DEPLOY_NAME));
        }
        deploymentApiService.apply(deployment);
        return true;
    }

    @Override
    public String meta() {
        String meta = null;
        String pluginMetaPath = "plugin/meta/k8s-deploy-meta.json";
        Resource resource = new ClassPathResource(pluginMetaPath);
        if (resource.exists()) {
            try {
                meta = IOUtils.toString(resource.getInputStream(), "UTF-8");
            } catch (IOException ignore) {
                logger.warn("读取插件元配置文件[{}]出现异常,异常详细如下:[\n{},\n]", pluginMetaPath, ignore);
            }
        }
        return meta;
    }

    @Override
    public String name() {
        return K8S_DEPLOYMENT_ACTION;
    }
}