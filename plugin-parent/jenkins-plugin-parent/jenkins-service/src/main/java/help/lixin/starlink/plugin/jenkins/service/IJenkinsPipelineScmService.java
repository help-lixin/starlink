package help.lixin.starlink.plugin.jenkins.service;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineScm;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsPipelineScmDTO;

public interface IJenkinsPipelineScmService {

    Integer saveScm(JenkinsPipelineScmDTO jenkinsPipelineScmDTO);

    Integer changeStatus(Long id,Integer status,String updateBy);

    JenkinsPipelineScm queryScmInfo(Long id);

}
