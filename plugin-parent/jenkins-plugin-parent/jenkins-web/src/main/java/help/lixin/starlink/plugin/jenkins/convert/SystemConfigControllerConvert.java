package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsSystemConfigPageDTO;
import help.lixin.starlink.plugin.jenkins.dto.sys.JenkinsSystemConfigDTO;
import help.lixin.starlink.plugin.jenkins.request.build.JenkinsSystemConfigPageVO;
import help.lixin.starlink.plugin.jenkins.request.build.JenkinsSystemConfigVO;
import org.mapstruct.Mapper;

@Mapper
public interface SystemConfigControllerConvert {

    JenkinsSystemConfigDTO convert(JenkinsSystemConfigVO jenkinsSystemConfigVO);

    JenkinsSystemConfigPageDTO convert(JenkinsSystemConfigPageVO jenkinsSystemConfigPageVO);

}
