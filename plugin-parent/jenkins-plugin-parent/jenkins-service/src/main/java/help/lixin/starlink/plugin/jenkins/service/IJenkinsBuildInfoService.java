package help.lixin.starlink.plugin.jenkins.service;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsBuildInfo;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsBuildInfoDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsBuildPageListDTO;
import help.lixin.response.PageResponse;

import java.util.List;

public interface IJenkinsBuildInfoService {

    Integer saveBuildIfo(JenkinsBuildInfoDTO jenkinsBuildInfoDTO);

    List<JenkinsBuildInfo> queryBuildInfoList(Long jobId);

    JenkinsBuildInfo queryBuildInfo(Long id);

    Integer changeStatus(Long id,Integer status,String updateBy);

    PageResponse<JenkinsBuildInfo> pageList(JenkinsBuildPageListDTO jenkinsBuildPageListDTO);
}
