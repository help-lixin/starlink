package help.lixin.starlink.plugin.jenkins.service;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupCommon;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupCommonDTO;

import java.util.List;

public interface IJenkinsPipelineSetupCommonService {

    Integer save(JenkinsPipelineSetupCommonDTO jenkinsPipelineSetupCommonDTO);

    Integer changeStatus(Long id,Integer status,String updateBy);

    JenkinsPipelineSetupCommon queryInfo(Long id);

    Boolean saveList(List<JenkinsPipelineSetupCommonDTO> jenkinsPipelineSetupCommonDTOS,
                     Long jobId, String createBy);

    List<JenkinsPipelineSetupCommon> querySetupList(Long jobId);

}