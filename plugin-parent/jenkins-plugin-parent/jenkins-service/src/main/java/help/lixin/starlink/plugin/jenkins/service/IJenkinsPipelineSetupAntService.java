package help.lixin.starlink.plugin.jenkins.service;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupAnt;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupAntDTO;

public interface IJenkinsPipelineSetupAntService {

    Integer saveAnt(JenkinsPipelineSetupAntDTO jenkinsPipelineSetupAntDTO);

    Integer changeStatus(Long id,Integer status,String updateBy);

    JenkinsPipelineSetupAnt queryAntInfo(Long id);
}