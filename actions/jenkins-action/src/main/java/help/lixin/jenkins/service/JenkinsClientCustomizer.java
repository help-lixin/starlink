package help.lixin.jenkins.service;

import com.cdancy.jenkins.rest.JenkinsClient;
import help.lixin.jenkins.properties.JenkinsProperties;

public interface JenkinsClientCustomizer {
    public void customizer(JenkinsProperties properties, JenkinsClient client);
}
