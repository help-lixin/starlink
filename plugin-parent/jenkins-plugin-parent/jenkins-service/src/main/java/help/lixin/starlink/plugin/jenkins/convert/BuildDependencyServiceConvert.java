package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsBuildDependency;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsBuildDependencyDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BuildDependencyServiceConvert {

    JenkinsBuildDependency convert(JenkinsBuildDependencyDTO jenkinsBuildDependencyDTO);
}
