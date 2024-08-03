package help.lixin.starlink.plugin.gitlab.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.starlink.core.constants.Constant;
import help.lixin.starlink.plugin.gitlab.action.entity.GitlabActionParams;

/**
 * gitlab 配置实现
 */
public class GitlabConfigAction implements Action {
    public static final String GITLAB_ACTION_CONFIG = "gitlab-config";

    private Logger logger = LoggerFactory.getLogger(GitlabConfigAction.class);

    public GitlabConfigAction() {}

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        logger.debug("start execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        String stageParams = ctx.getStageParams();
        ObjectMapper objectMapper = new ObjectMapper();
        GitlabActionParams gitlabProperties = objectMapper.readValue(stageParams, GitlabActionParams.class);
        ctx.addVar(Constant.SourceCodeRepository.REPOSITORY_TYPE, "gitlab");
        ctx.addVar("projectName", gitlabProperties.getProjectName());
        ctx.addVar("branch", gitlabProperties.getBranch());
        ctx.addVar("url", gitlabProperties.getUrl());
        logger.debug("end execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        return true;
    }

    @Override
    public String name() {
        return GITLAB_ACTION_CONFIG;
    }
}
