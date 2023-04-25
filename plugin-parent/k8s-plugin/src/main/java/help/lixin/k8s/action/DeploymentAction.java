package help.lixin.k8s.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.k8s.constants.Constant;
import help.lixin.k8s.service.K8SFaceService;
import io.fabric8.kubernetes.api.model.Container;
import io.fabric8.kubernetes.api.model.EnvVar;
import io.fabric8.kubernetes.api.model.EnvVarBuilder;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DeploymentAction implements Action {
    private Logger logger = LoggerFactory.getLogger(DeploymentAction.class);
    public static final String K8S_DEPLOYMENT_ACTION = "k8s-deploy";

    private K8SFaceService k8sFaceService;

    public DeploymentAction(K8SFaceService k8sFaceService) {
        this.k8sFaceService = k8sFaceService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        if (logger.isDebugEnabled()) {
            logger.debug("start execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        }

        // 1. 参数解析
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        DeploymentParams params = mapper.readValue(stageParams, DeploymentParams.class);

        // 2. 加载模板文件
        StringWriter yamlTemplateContent = new StringWriter();
        IOUtils.copy(new FileInputStream(new File(params.getYamlTemplatePath())), yamlTemplateContent, StandardCharsets.UTF_8);

        // 3. 变量
        Map<String, Object> tempContext = new HashMap<>(ctx.getVars());

        // 系统要求的变量
        tempContext.put(Constant.NAMESPACE, null == params.getNamespace() ? Constant.DEFAULT_NAMESPACE : varProcess(params.getNamespace(), tempContext));
        tempContext.put(Constant.DEPLOYMENT_NAME, varProcess(params.getDeployName(), tempContext));   // 部署名称
        tempContext.put(Constant.POD_LABEL_NAME, varProcess(params.getPodLabelName(), tempContext));   // 标签名称
        tempContext.put(Constant.POD_LABEL_VALUE, varProcess(params.getPodLabelValue(), tempContext)); // 标签Value
        tempContext.put(Constant.IMAGE_PULL_SECRET_NAME, varProcess(params.getImagePullSecretName(), tempContext)); // 拉取镜像的Secret名称
        tempContext.put(Constant.IMAGE, varProcess(params.getImage(), tempContext));   // 镜像
        tempContext.put(Constant.CONTAINER_NAME, varProcess(params.getContainerName(), tempContext));  // pod名称
        tempContext.put(Constant.PORT, varProcess(params.getPort(), tempContext));         // pod端口

        // 填充用户自己写的变量名和变量值
        paddingContext(params.getVars(), tempContext);

        // 4. 把yaml中的变量进行替换
        String ymlContent = varProcess(yamlTemplateContent.toString(), tempContext);

        if (logger.isDebugEnabled()) {
            logger.debug("ready apply yaml:\n{}", ymlContent);
        }

        // 5. 根据yml模板转换成:Deployment对象,目的是还需要填充一些其它信息到Deployment上
        Deployment deployment = k8sFaceService.getDeploymentApiService().yamlConvertDeployment(ymlContent);

        // 6. 填充env和lables到:Deployment上
        paddingEnv(deployment, params.getEnvs(), tempContext);
        paddingLables(deployment, params.getLabels(), tempContext);

        // 7. 向K8S,提交Deployment
        k8sFaceService.getDeploymentApiService().apply(deployment);
        if (logger.isDebugEnabled()) {
            logger.debug("end execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        }
        return true;
    }

    protected String varProcess(String varValue, Map<String, Object> tempContext) throws Exception {
        if (null != varValue) {
            String var = k8sFaceService.getExpressionService().prase(varValue, tempContext);
            return var;
        }
        return varValue;
    }


    protected void paddingLables(Deployment deployment, List<Label> labelList, Map<String, Object> tempContext) throws Exception {
        Map<String, String> deploylabels = deployment.getSpec().getSelector().getMatchLabels();
        Map<String, String> podlabels = deployment.getSpec().getTemplate().getMetadata().getLabels();
        if (null != labelList && labelList.isEmpty()) {
            for (Label label : labelList) {
                deploylabels.put(varProcess(label.getKey(), tempContext), varProcess(label.getValue(), tempContext));
                podlabels.put(varProcess(label.getKey(), tempContext), varProcess(label.getValue(), tempContext));
            }
        }
    }


    protected void paddingEnv(Deployment deployment, List<DeploymentEnv> envs, Map<String, Object> tempContext) throws Exception {
        if (null != envs && !envs.isEmpty()) {
            // 定位到多个容器
            List<Container> containers = deployment.getSpec().getTemplate().getSpec().getContainers();
            // 遍历所有的变量
            for (DeploymentEnv env : envs) {
                for (Container container : containers) {
                    // 容器名称
                    String containerName = container.getName();
                    List<EnvVar> envVars = container.getEnv();
                    String paramContainerName = varProcess(env.getContainerName(), tempContext);
                    if (paramContainerName.equals(containerName)) {
                        envVars.add(new EnvVarBuilder()
                                //
                                .withName(varProcess(env.getName(), tempContext))
                                //
                                .withValue(varProcess(env.getValue(), tempContext))
                                //
                                .build());
                    }
                }
            }
        }
    }

    protected void paddingContext(List<DeploymentVar> vars, Map<String, Object> tempContext) throws Exception {
        if (null != vars && !vars.isEmpty()) {
            for (DeploymentVar var : vars) {
                tempContext.put(varProcess(var.getName(), tempContext), varProcess(var.getValue(), tempContext));
            }
        }
    }

    @Override
    public String name() {
        return K8S_DEPLOYMENT_ACTION;
    }
}
