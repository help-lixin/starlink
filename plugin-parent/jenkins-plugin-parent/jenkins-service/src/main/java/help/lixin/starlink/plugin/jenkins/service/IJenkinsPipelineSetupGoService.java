package help.lixin.starlink.plugin.jenkins.service;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupGo;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupGoDTO;

public interface IJenkinsPipelineSetupGoService{
    Integer saveGo(JenkinsPipelineSetupGoDTO jenkinsPipelineSetupGoDTO);

    Integer changeStatus(Long id,Integer status,String updateBy);

    JenkinsPipelineSetupGo queryGoInfo(Long id);
}