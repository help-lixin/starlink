package help.lixin.starlink.plugin.jenkins.service;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsJob;
import help.lixin.starlink.plugin.jenkins.dto.job.BuildDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobFormDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobFormDetailDTO;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobPageListDTO;
import help.lixin.response.PageResponse;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/14 8:41 下午
 * @Description
 */
public interface IJenkinsJobService {

    Long saveJob(JenkinsJobFormDTO jenkinsJobFormDTO);

    Integer changeStatus(Long id,Integer status,String updateBy);

    JenkinsJob queryJobInfo(Long id);

    JenkinsJobFormDetailDTO queryJobDetail(Long id);

    PageResponse<JenkinsJob> pageList(JenkinsJobPageListDTO jenkinsJobPageListDTO);

    List<String> queryJobOptionList(String instanceCode);

    void checkCredentialsId(String instanceCode,String credentialsId,String path,String jobName);

    Integer buildJob(BuildDTO buildDTO);

    Boolean jobNameIsExist(String jobName,String instanceCode);

    Boolean removeJob(Long jobId);

}
