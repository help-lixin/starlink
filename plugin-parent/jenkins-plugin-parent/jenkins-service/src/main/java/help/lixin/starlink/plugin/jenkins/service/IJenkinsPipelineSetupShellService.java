package help.lixin.starlink.plugin.jenkins.service;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupShell;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupShellDTO;

public interface IJenkinsPipelineSetupShellService {
    Integer saveShell(JenkinsPipelineSetupShellDTO jenkinsPipelineSetupShellDTO);

    Integer changeStatus(Long id,Integer status,String updateBy);

    JenkinsPipelineSetupShell queryShellInfo(Long id);
}