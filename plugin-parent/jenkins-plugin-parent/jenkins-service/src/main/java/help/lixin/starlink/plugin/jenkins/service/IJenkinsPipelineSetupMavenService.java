package help.lixin.starlink.plugin.jenkins.service;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupMaven;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupMavenDTO;

public interface IJenkinsPipelineSetupMavenService {

    Integer saveMaven(JenkinsPipelineSetupMavenDTO jenkinsPipelineSetupMavenDTO);

    Integer changeStatus(Long id,Integer status,String updateBy);

    JenkinsPipelineSetupMaven queryMavenInfo(Long id);
}