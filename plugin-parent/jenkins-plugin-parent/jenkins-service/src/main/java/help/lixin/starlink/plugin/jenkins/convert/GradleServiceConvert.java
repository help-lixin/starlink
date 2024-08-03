package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupGradle;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupGradleDTO;
import org.mapstruct.Mapper;

@Mapper
public interface GradleServiceConvert {

    JenkinsPipelineSetupGradle convert(JenkinsPipelineSetupGradleDTO jenkinsPipelineSetupGradleDTO);
}
