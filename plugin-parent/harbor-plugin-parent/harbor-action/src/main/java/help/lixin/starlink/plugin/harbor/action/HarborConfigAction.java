package help.lixin.starlink.plugin.harbor.action;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.log.Log;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.service.IExpressionService;
import help.lixin.starlink.core.constants.Constant;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.harbor.action.domain.HarborActionParams;
import help.lixin.starlink.plugin.harbor.api.properties.HarborProperties;
import help.lixin.starlink.plugin.harbor.domain.HarborProject;
import help.lixin.starlink.plugin.harbor.service.IHarborProjectService;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class HarborConfigAction implements Action {

    private Logger logger = LoggerFactory.getLogger(HarborConfigAction.class);

    public static final String HARBOR_CONFIG_ACTION = "harbor-config";

    private IHarborProjectService harborProjectService;

    private AbstractServiceFactory harborServiceFactory;

    private IExpressionService expressionService;

    private StringEncryptor stringEncryptor;

    public HarborConfigAction(IHarborProjectService harborProjectService, //
        AbstractServiceFactory harborServiceFactory, //
        StringEncryptor stringEncryptor, //
        IExpressionService expressionService) {
        this.harborProjectService = harborProjectService;
        this.harborServiceFactory = harborServiceFactory;
        this.stringEncryptor = stringEncryptor;
        this.expressionService = expressionService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        logger.info("开始执行启用Harbor插件");
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        // 忽略未知字段
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        HarborActionParams harborActionParams = mapper.readValue(stageParams, HarborActionParams.class);
        Long projectId = harborActionParams.getProjectId();
        String projectName = "library";
        HarborProject harborProject = harborProjectService.getHarborProject(projectId);
        if (null != harborProject) {
            projectName = harborProject.getProjectName();
        }

        RepositoryInfo repositoryInfo = getRepositoryInfo(harborActionParams);
        if (null != repositoryInfo) {
            String repositoryUrlVar = null;
            String url = repositoryInfo.getUrl();
            if (url.startsWith("http://")) {
                repositoryUrlVar = url.replaceAll("http://", "");
            } else if (url.startsWith("https://")) {
                repositoryUrlVar = url.replaceAll("https://", "");
            }
            if (repositoryUrlVar.endsWith("/")) {
                repositoryUrlVar = repositoryUrlVar.substring(0, repositoryUrlVar.length() - 1);
            }
            repositoryInfo.setUrl(repositoryUrlVar);
        }
        ctx.addVar(Constant.ImageRepository.IMAGE_PROJECT, projectName);
        ctx.addVar(Constant.ImageRepository.IMAGE_REPOSITORY, repositoryInfo.getUrl());
        ctx.addVar(Constant.ImageRepository.IMAGE_USERNAME, repositoryInfo.getUserName());
        ctx.addVar(Constant.ImageRepository.IMAGE_PASSWORD, repositoryInfo.getPassword());
        ctx.addVar(Constant.ImageRepository.IMAGE_PASSWORD_IS_ENCRYPTION, harborActionParams.isEncryption() ? //
            Constant.ImageRepository.YES : //
            Constant.ImageRepository.NO);

        String harborTraceMsg = String.format("启用,镜像仓库:[%s],用户名:[%s],密码:[%s]", repositoryInfo.getUrl(), //
            repositoryInfo.getUserName(), //
            RandomStringUtils.random(repositoryInfo.getPassword().length(), "*"));
        Log.trace(harborTraceMsg);
        logger.info(harborTraceMsg);
        logger.info("启用Harbor插件执行结束");
        return true;
    }

    @Override
    public String meta() {
        String meta = null;
        String pluginMetaPath = "plugin/meta/harbor-meta.json";
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
        return HARBOR_CONFIG_ACTION;
    }

    protected RepositoryInfo getRepositoryInfo(HarborActionParams harborActionParams) {
        String instance = harborActionParams.getInstanceCode();
        Boolean isEncryption = harborActionParams.isEncryption();
        HarborProperties harborProperties = harborServiceFactory.getInstance(instance, HarborProperties.class);
        RepositoryInfo repositoryInfo = new RepositoryInfo();
        repositoryInfo.setUrl(harborProperties.getUrl());
        repositoryInfo.setUserName(harborProperties.getUserName());
        if (isEncryption) {
            String encryptPwd = stringEncryptor.encrypt(harborProperties.getPassword());
            repositoryInfo.setPassword(encryptPwd);
        } else {
            repositoryInfo.setPassword(harborProperties.getPassword());
        }
        return repositoryInfo;
    }
}

class RepositoryInfo {
    private String url;
    private String userName;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
