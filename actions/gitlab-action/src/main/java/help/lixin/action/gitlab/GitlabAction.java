package help.lixin.action.gitlab;

import com.fasterxml.jackson.databind.ObjectMapper;
import help.lixin.action.gitlab.properties.GitlabProperties;
import help.lixin.core.pipeline.action.Action;
import help.lixin.core.pipeline.ctx.PipelineContext;

/**
 * gitlab实现
 */
public class GitlabAction implements Action {
    public static final String GITLAB_ACTION = "gitlab";

    @Override
    public boolean execute(PipelineContext ctx) throws Exception {
        String originParams = ctx.getOriginParams();
        ObjectMapper objectMapper = new ObjectMapper();
        GitlabProperties gitlabProperties = objectMapper.readValue(originParams, GitlabProperties.class);
        String projectName = gitlabProperties.getProjectName();
        if (null == projectName && null != gitlabProperties.getUrl()) {
            String url = gitlabProperties.getUrl();
            int startIndex = url.lastIndexOf("/");
            int endIndex = url.lastIndexOf(".");
            projectName = url.substring(startIndex + 1, endIndex);
        }

        ctx.addVar("projectName", projectName);
        ctx.addVar("branch", gitlabProperties.getBranch());
        ctx.addVar("url", gitlabProperties.getUrl());
        return true;
    }

    @Override
    public String name() {
        return GITLAB_ACTION;
    }
}
