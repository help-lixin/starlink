package help.lixin.starlink.plugin.jenkins.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsBuildDependency;

public interface JenkinsBuildDependencyMapper extends BaseMapper<JenkinsBuildDependency> {

    JenkinsBuildDependency queryByJobId(Long jobId);

    JenkinsBuildDependency queryByJob(@Param("jobName") String jobName, @Param("instanceCode") String instanceCode);
}