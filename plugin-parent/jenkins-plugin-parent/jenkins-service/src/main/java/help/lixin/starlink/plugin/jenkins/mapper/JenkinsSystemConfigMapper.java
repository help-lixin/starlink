package help.lixin.starlink.plugin.jenkins.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsSystemConfig;
import help.lixin.starlink.plugin.jenkins.dto.build.JenkinsSystemConfigPageDTO;

import java.util.List;

public interface JenkinsSystemConfigMapper extends BaseMapper<JenkinsSystemConfig> {

    Integer insertSelective(JenkinsSystemConfig jenkinsSystemConfig);

    List<JenkinsSystemConfig> queryList(JenkinsSystemConfigPageDTO jenkinsSystemConfigPageDTO);

    List<String> queryPluginTypeByInstanceCode(String instanceCode);

    List<String> queryInstanceCodes();

}