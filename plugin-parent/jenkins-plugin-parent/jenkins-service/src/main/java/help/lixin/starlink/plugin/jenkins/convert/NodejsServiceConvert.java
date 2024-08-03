package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupNodejs;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupNodejsDTO;
import org.mapstruct.Mapper;

@Mapper
public interface NodejsServiceConvert {

    JenkinsPipelineSetupNodejs convert(JenkinsPipelineSetupNodejsDTO jenkinsPipelineSetupNodejsDTO);
}
