package help.lixin.starlink.plugin.jenkins.service;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsBuildDependency;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsBuildDependencyDTO;

public interface IJenkinsBuildDependencyService {

    Integer saveDependency(JenkinsBuildDependencyDTO jenkinsBuildDependencyDTO);

    JenkinsBuildDependency dependencyInfo(Long id);

    JenkinsBuildDependency queryByJobId(Long jobId);


    Integer changeStatus(Long id,Integer status,String updateBy);
}
