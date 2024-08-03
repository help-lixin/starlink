package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupGo;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupGoDTO;
import org.mapstruct.Mapper;

@Mapper
public interface GoServiceConvert {

    JenkinsPipelineSetupGo convert(JenkinsPipelineSetupGoDTO jenkinsPipelineSetupGoDTO);
}
