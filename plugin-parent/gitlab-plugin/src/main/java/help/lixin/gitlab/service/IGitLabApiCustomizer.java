package help.lixin.gitlab.service;

import help.lixin.gitlab.properties.GitlabProperties;
import org.gitlab4j.api.GitLabApi;

public interface IGitLabApiCustomizer {

    void customizer(GitlabProperties properties, GitLabApi gitLabApi);
}
