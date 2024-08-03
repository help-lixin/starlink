package help.lixin.starlink.plugin.jenkins.service;

import java.util.List;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsParams;
import help.lixin.starlink.plugin.jenkins.dto.params.JenkinsParamDTO;

public interface IJenkinsParamsService {

    Integer saveParams(JenkinsParamDTO jenkinsParamDTO);

    List<JenkinsParams> queryParamList(Long jobId);

    JenkinsParams paramInfo(Long id);

    Integer changeStatus(Long id, Integer status, String updateBy);

    Boolean cleanParamsByJobId(Long jobId);

}
