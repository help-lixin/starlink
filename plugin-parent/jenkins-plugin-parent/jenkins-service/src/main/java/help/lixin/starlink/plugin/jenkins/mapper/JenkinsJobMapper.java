package help.lixin.starlink.plugin.jenkins.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsJob;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsBuildingJobInfo;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsJobPageListDTO;

public interface JenkinsJobMapper extends BaseMapper<JenkinsJob> {

    int insertSelective(JenkinsJob entity);

    List<JenkinsJob> pageList(JenkinsJobPageListDTO jenkinsJobPageListDTO);

    JenkinsJob queryJob(@Param("jobName") String jobName, @Param("instanceCode") String instanceCode);

    List<JenkinsBuildingJobInfo> queryBuildingJob();

    JenkinsJob queryByJobName(@Param("jobName") String jobName, @Param("instanceCode") String instanceCode);

    int insertJob(JenkinsJob jenkinsJob);

    int updateByPrimaryKeySelective(JenkinsJob jenkinsJob);

}