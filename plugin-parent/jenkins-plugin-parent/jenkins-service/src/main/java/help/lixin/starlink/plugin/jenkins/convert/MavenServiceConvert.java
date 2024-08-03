package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupMaven;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupMavenDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MavenServiceConvert {

    JenkinsPipelineSetupMaven convert(JenkinsPipelineSetupMavenDTO jenkinsPipelineSetupMavenDTO);
}
