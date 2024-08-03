package help.lixin.starlink.plugin.jenkins.service;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupNodejs;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupNodejsDTO;

public interface IJenkinsPipelineSetupNodejsService{

    Integer saveNodejs(JenkinsPipelineSetupNodejsDTO jenkinsPipelineSetupNodejsDTO);

    Integer changeStatus(Long id,Integer status,String updateBy);

    JenkinsPipelineSetupNodejs queryNodejsInfo(Long id);
}