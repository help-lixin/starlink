package help.lixin.starlink.plugin.jenkins.convert;

import help.lixin.starlink.plugin.jenkins.convert.rule.JobRule;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsJob;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobFormDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobFormDetailDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = JobRule.class)
public interface JobServiceConvert {

    @Mapping(source = "toolsType", target = "tools")
    @Mapping(source = "scmType", target = "scm")
    @Mapping(source = "jdkId", target = "jdk")
    JenkinsJob convert(JenkinsJobFormDTO jenkinsJobFormDTO);

    @Mapping(source = "tools", target = "toolsType")
    @Mapping(source = "scm", target = "scmType")
    @Mapping(source = "jdk", target = "jdkId")
    JenkinsJobFormDetailDTO convert(JenkinsJob jenkinsJob);
}
