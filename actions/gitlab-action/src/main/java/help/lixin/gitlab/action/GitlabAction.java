package help.lixin.gitlab.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;
import help.lixin.gitlab.service.GitlabFaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * gitlab实现
 */
public class GitlabAction implements Action {
    public static final String GITLAB_ACTION = "gitlab";

    private Logger logger = LoggerFactory.getLogger(GitlabAction.class);

    private GitlabFaceService gitlabFaceService;

    public GitlabAction(GitlabFaceService gitlabFaceService) {
        this.gitlabFaceService = gitlabFaceService;
    }


    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        logger.debug("start execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        String stageParams = ctx.getStageParams();
        ObjectMapper objectMapper = new ObjectMapper();
        GitlabActionParams gitlabProperties = objectMapper.readValue(stageParams, GitlabActionParams.class);
        ctx.addVar("projectName", gitlabProperties.getProjectName());
        ctx.addVar("branch", gitlabProperties.getBranch());
        ctx.addVar("url", gitlabProperties.getUrl());
        ctx.addVar("version", gitlabProperties.getBranch());
        logger.debug("end execute action: [{}],ctx:[{}]", this.getClass().getName(), ctx);
        return true;
    }

    @Override
    public String name() {
        return GITLAB_ACTION;
    }
}
