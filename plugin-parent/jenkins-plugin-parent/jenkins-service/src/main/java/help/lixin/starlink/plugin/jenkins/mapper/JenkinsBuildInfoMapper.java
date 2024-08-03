package help.lixin.starlink.plugin.jenkins.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import help.lixin.starlink.plugin.jenkins.domain.JenkinsBuildInfo;
import help.lixin.starlink.plugin.jenkins.dto.job.JenkinsBuildPageListDTO;

public interface JenkinsBuildInfoMapper extends BaseMapper<JenkinsBuildInfo> {
    int insertBuildInfo(JenkinsBuildInfo record);

    int insertSelective(JenkinsBuildInfo record);

    List<JenkinsBuildInfo> pageList(JenkinsBuildPageListDTO jenkinsBuildPageListDTO);

    JenkinsBuildInfo queryBuildInfo(@Param("jobName") String jobName, @Param("logId") Integer logId,
        @Param("instanceCode") String instanceCode);

}