package help.lixin.starlink.plugin.jsch.action;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.log.Log;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.starlink.core.constants.Constant;
import help.lixin.starlink.core.plugin.service.AbstractServiceFactory;
import help.lixin.starlink.plugin.jsch.action.domain.config.DockerLoginActionParams;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jasypt.encryption.StringEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class SSHDockerLoginConfigAction implements Action {
    private final Logger logger = LoggerFactory.getLogger(SSHDeployAction.class);

    public static final String SSH_DOCKER_LOGIN = "ssh-docker-login";

    private final AbstractServiceFactory jschServiceFactory;

    private StringEncryptor stringEncryptor;

    public SSHDockerLoginConfigAction(AbstractServiceFactory jschServiceFactory, //
                                      StringEncryptor stringEncryptor) {
        this.jschServiceFactory = jschServiceFactory;
        this.stringEncryptor = stringEncryptor;
    }

    public void setStringEncryptor(StringEncryptor stringEncryptor) {
        this.stringEncryptor = stringEncryptor;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        logger.info("开始配置镜像仓库信息");
        String stageParams = ctx.getStageParams();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        DockerLoginActionParams params = mapper.readValue(stageParams, DockerLoginActionParams.class);
        String repositoryUrlVar = params.getImageRepository();
        String projectName = params.getNamespace();
        String userName = params.getUserName();
        String password = params.getPassword();
        if (StringUtils.isEmpty(repositoryUrlVar) || //
                StringUtils.isEmpty(projectName)) {
            throw new Exception("镜像仓库和命名空间不允许为空");
        }
        repositoryUrlVar = processRepositoryUrlVar(repositoryUrlVar);
        if (null != password && params.getEncryption()) {
            password = stringEncryptor.encrypt(params.getPassword());
        }

        ctx.addVar(Constant.ImageRepository.IMAGE_PROJECT, projectName);
        ctx.addVar(Constant.ImageRepository.IMAGE_REPOSITORY, repositoryUrlVar);
        ctx.addVar(Constant.ImageRepository.IMAGE_USERNAME, userName);
        ctx.addVar(Constant.ImageRepository.IMAGE_PASSWORD, password);
        ctx.addVar(Constant.ImageRepository.IMAGE_PASSWORD_IS_ENCRYPTION, params.getEncryption() ? //
                Constant.ImageRepository.YES : //
                Constant.ImageRepository.NO);

        String traceMsg = String.format("启用,镜像仓库:[%s]/[%s],用户名:[%s],密码:[%s]", repositoryUrlVar, projectName,  //
                userName, //
                RandomStringUtils.random(password.length(), "*"));
        Log.trace(traceMsg);
        logger.info("配置镜像仓库信息结束");
        return true;
    }

    protected String processRepositoryUrlVar(String repositoryUrlVar) {
        if (repositoryUrlVar.startsWith("http://")) {
            repositoryUrlVar = repositoryUrlVar.replaceAll("http://", "");
        } else if (repositoryUrlVar.startsWith("https://")) {
            repositoryUrlVar = repositoryUrlVar.replaceAll("https://", "");
        }
        if (repositoryUrlVar.endsWith("/")) {
            repositoryUrlVar = repositoryUrlVar.substring(0, repositoryUrlVar.length() - 1);
        }
        return repositoryUrlVar;
    }

    @Override
    public String meta() {
        String meta = null;
        String pluginMetaPath = "plugin/meta/ssh-docker-login-meta.json";
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
        return SSH_DOCKER_LOGIN;
    }
}
