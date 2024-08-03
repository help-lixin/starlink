package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsPipelineScmDTO;
import help.lixin.starlink.plugin.jenkins.request.build.JenkinsPipelineScmVO;
import org.mapstruct.Mapper;

@Mapper
public interface ScmControllerConvert {

    JenkinsPipelineScmDTO convert(JenkinsPipelineScmVO jenkinsPipelineScmVO);
}
