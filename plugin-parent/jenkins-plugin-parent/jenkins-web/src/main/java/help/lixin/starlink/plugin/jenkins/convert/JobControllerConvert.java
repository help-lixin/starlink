package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.dto.job.BuildDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobFormDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobPageListDTO;
import help.lixin.starlink.plugin.jenkins.request.job.BuildVO;
import help.lixin.starlink.plugin.jenkins.request.job.JenkinsJobFormVO;
import help.lixin.starlink.plugin.jenkins.request.job.JenkinsJobPageListVO;
import org.mapstruct.Mapper;

@Mapper
public interface JobControllerConvert {

    JenkinsJobFormDTO convert(JenkinsJobFormVO jenkinsJobFormVO);

    JenkinsJobPageListDTO convert(JenkinsJobPageListVO jenkinsJobPageListVO);

    BuildDTO convert(BuildVO buildVO);

}
