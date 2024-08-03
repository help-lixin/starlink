package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsPipelineScm;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsPipelineScmDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ScmServiceConvert {

    JenkinsPipelineScm convert(JenkinsPipelineScmDTO jenkinsPipelineScmDTO);
}
