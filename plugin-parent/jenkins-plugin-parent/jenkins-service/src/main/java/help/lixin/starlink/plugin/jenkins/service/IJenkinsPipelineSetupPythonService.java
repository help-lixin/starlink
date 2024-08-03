package help.lixin.starlink.plugin.jenkins.service;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupPython;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupPythonDTO;

public interface IJenkinsPipelineSetupPythonService {

    Integer savePython(JenkinsPipelineSetupPythonDTO jenkinsPipelineSetupPythonDTO);

    Integer changeStatus(Long id,Integer status,String updateBy);

    JenkinsPipelineSetupPython queryPythonInfo(Long id);
}