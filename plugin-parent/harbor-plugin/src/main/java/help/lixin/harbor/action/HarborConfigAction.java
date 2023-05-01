package help.lixin.harbor.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.constants.Constant;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.harbor.action.entity.HarborActionParams;
import help.lixin.harbor.properties.HarborProperties;
import help.lixin.harbor.service.HarborFaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HarborConfigAction implements Action {

    private Logger logger = LoggerFactory.getLogger(HarborConfigAction.class);

    public static final String HARBOR_CONFIG_ACTION = "harbor-config";

    private HarborFaceService harborFaceService;

    public HarborConfigAction(HarborFaceService harborFaceService) {
        this.harborFaceService = harborFaceService;
    }

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        logger.debug("start execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        RepositoryInfo repositoryInfo = getRepositoryInfo(ctx);
        if (null != repositoryInfo) {
            String repositoryUrlVar = null;
            String url = repositoryInfo.getUrl();
            if (url.startsWith("http://")) {
                repositoryUrlVar = repositoryInfo.getUrl().replaceAll("http://", "");
            } else if (url.startsWith("https://")) {
                repositoryUrlVar = repositoryInfo.getUrl().replaceAll("https://", "");
            }
            ctx.getVars().put(Constant.ImageRepository.REPOSITORY_URL, repositoryUrlVar);
        }

        ctx.getVars().put(Constant.ImageRepository.HTTP_REPOSITORY_URL, repositoryInfo.getUrl());
        ctx.getVars().put(Constant.ImageRepository.REPOSITORY_USERNAME, repositoryInfo.getUserName());
        ctx.getVars().put(Constant.ImageRepository.REPOSITORY_PASSWORD, repositoryInfo.getPassword());
        logger.debug("end execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        return true;
    }


    @Override
    public String name() {
        return HARBOR_CONFIG_ACTION;
    }

    protected RepositoryInfo getRepositoryInfo(PipelineContext ctx) {
        RepositoryInfo repositoryInfo = null;
        String stageParams = ctx.getStageParams();
        // 1. 先从请求参数里获取,获取不到的情况下,读取系统默认的配置
        if (null != stageParams && !stageParams.isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                HarborActionParams harborActionParams = objectMapper.readValue(stageParams, HarborActionParams.class);
                repositoryInfo = new RepositoryInfo();
                repositoryInfo.setUrl(harborActionParams.getUrl());
                repositoryInfo.setUserName(harborActionParams.getUserName());
                repositoryInfo.setPassword(harborActionParams.getPassword());
                return repositoryInfo;
            } catch (Exception ignore) {
            }
        }

        // 2. 读取系统默认配置
        HarborProperties harborProperties = harborFaceService.getHarborProperties();
        repositoryInfo = new RepositoryInfo();
        repositoryInfo.setUrl(harborProperties.getUrl());
        repositoryInfo.setUserName(harborProperties.getUserName());
        repositoryInfo.setPassword(harborProperties.getPassword());
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
