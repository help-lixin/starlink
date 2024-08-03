package help.lixin.starlink.plugin.gitlab.api.service;

import org.gitlab4j.api.GitLabApi;

import help.lixin.starlink.plugin.gitlab.api.properties.GitlabProperties;

public interface IGitLabApiCustomizer {

    void customizer(GitlabProperties properties, GitLabApi gitLabApi);
}
