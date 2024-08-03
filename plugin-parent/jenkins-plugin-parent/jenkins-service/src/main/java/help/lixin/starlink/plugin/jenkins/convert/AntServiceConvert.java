package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineSetupAnt;
import help.lixin.starlink.plugin.jenkins.dto.setup.JenkinsPipelineSetupAntDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AntServiceConvert {

    JenkinsPipelineSetupAnt convert(JenkinsPipelineSetupAntDTO jenkinsPipelineSetupAntDTO);
}
