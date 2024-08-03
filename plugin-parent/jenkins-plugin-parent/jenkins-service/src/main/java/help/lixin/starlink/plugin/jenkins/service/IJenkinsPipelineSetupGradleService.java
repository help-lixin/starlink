package help.lixin.starlink.plugin.jenkins.service;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupGradle;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupGradleDTO;

public interface IJenkinsPipelineSetupGradleService {

    Integer saveGradle(JenkinsPipelineSetupGradleDTO jenkinsPipelineSetupGradleDTO);

    Integer changeStatus(Long id,Integer status,String updateBy);

    JenkinsPipelineSetupGradle queryGradleInfo(Long id);
}