package help.lixin.starlink.plugin.jenkins.api.service;

import com.cdancy.jenkins.rest.JenkinsClient;
import help.lixin.starlink.plugin.jenkins.api.properties.JenkinsProperties;

public interface JenkinsClientCustomizer {
    public void customizer(JenkinsProperties properties, JenkinsClient client);
}
