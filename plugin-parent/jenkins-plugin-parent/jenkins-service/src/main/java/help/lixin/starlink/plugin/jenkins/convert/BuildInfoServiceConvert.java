package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsBuildInfo;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsBuildInfoDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BuildInfoServiceConvert {

    JenkinsBuildInfo convert(JenkinsBuildInfoDTO jenkinsBuildInfoDTO);
}
