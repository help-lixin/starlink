package help.lixin.starlink.plugin.jenkins.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsPluginInit;
import help.lixin.starlink.plugin.jenkins.dto.plugin.JenkinsPluginPageListDTO;

import java.util.List;

public interface JenkinsPluginInitMapper extends BaseMapper<JenkinsPluginInit> {

    List<JenkinsPluginInit> queryInitPlugin(JenkinsPluginPageListDTO jenkinsPluginPageListDTO);
}