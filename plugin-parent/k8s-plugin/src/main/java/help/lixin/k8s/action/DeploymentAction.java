package help.lixin.k8s.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.k8s.action.entity.DeploymentEnv;
import help.lixin.k8s.action.entity.DeploymentParams;
import help.lixin.k8s.action.entity.DeploymentVar;
import help.lixin.k8s.action.entity.Label;
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
import java.util.ArrayList;
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
        logger.info("开始执行K8S Deployment插件.");

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
        tempContext.put(Constant.IMAGE_PULL_SECRET_NAME, varProcess(params.getImagePullSecretName(), tempContext)); // 拉取镜像的Secret名称
        tempContext.put(Constant.IMAGE, varProcess(params.getImage(), tempContext));   // 镜像
        tempContext.put(Constant.CONTAINER_NAME, varProcess(params.getContainerName(), tempContext));  // pod名称
        tempContext.put(Constant.PORT, varProcess(params.getPort(), tempContext));         // pod端口

        // 填充用户自己写的变量名和变量值
        appendVars(params.getVars(), tempContext);

        // 为容器配置环境变量
        List<DeploymentEnv> envs = processEnv(params.getEnvs(), tempContext);

        // 为Deployment配置标签
        Map<String, String> lables = processLables(params.getLabels(), tempContext);
        lables.put("app", (String) ctx.getVars().get("projectName"));

        tempContext.put("envs", envs);
        tempContext.put("lables", lables);

        // 4. 把yaml中的变量进行替换
        String ymlContent = k8sFaceService.getDeploymentTemplateService()//
                .process(yamlTemplateContent.toString(), tempContext);

        logger.info("向K8S提交如下YAML内容:\n{}", ymlContent);

        // 5. 根据yml模板转换成:Deployment对象
        Deployment deployment = k8sFaceService.getDeploymentApiService() //
                .yamlConvertDeployment(ymlContent);

        // 6. 向K8S,提交Deployment
        k8sFaceService.getDeploymentApiService().apply(deployment);
        logger.info("结束K8S Deployment插件的执行.");
        return true;
    }

    protected String varProcess(String varValue, Map<String, Object> tempContext) throws Exception {
        varValue = k8sFaceService.getExpressionService().prase(varValue, tempContext);
        return varValue;
    }

    protected Map<String, String> processLables(List<Label> labelList,
                                                //
                                                Map<String, Object> tempContext) throws Exception {
        Map<String, String> lables = new HashMap<>(0);
        if (null != labelList && !labelList.isEmpty()) {
            for (Label label : labelList) {
                lables.put( //
                        varProcess(label.getKey(), tempContext), //
                        varProcess(label.getValue(), tempContext));
            }
        }
        return lables;
    }


    protected List<DeploymentEnv> processEnv(List<DeploymentEnv> envs, Map<String, Object> tempContext) throws Exception {
        if (null != envs && !envs.isEmpty()) {
            // 遍历所有的变量
            for (DeploymentEnv env : envs) {
                env.setContainerName(varProcess(env.getContainerName(), tempContext));
                env.setName(varProcess(env.getName(), tempContext));
                env.setValue(varProcess(env.getValue(), tempContext));
            }
        }
        return envs;
    }

    protected void appendVars(List<DeploymentVar> vars, Map<String, Object> tempContext) throws Exception {
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