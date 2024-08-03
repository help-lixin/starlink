package help.lixin.starlink.plugin.jenkins.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.jenkins.domain.JenkinsParams;

import java.util.List;

public interface JenkinsParamsMapper extends BaseMapper<JenkinsParams> {

    List<JenkinsParams> queryByJobId(Long jobId);
}