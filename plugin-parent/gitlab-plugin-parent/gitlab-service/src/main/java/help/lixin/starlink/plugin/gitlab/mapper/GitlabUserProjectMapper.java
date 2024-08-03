package help.lixin.starlink.plugin.gitlab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.gitlab.domain.GitlabUserProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GitlabUserProjectMapper extends BaseMapper<GitlabUserProject> {

    GitlabUserProject selectByUserIdAndProjectId(@Param("userId") Long userId,@Param("projectId") Long projectId);

    GitlabUserProject queryProjectByProjectId(Long projectId);

    List<Long> queryUserIdByProjectId(Long projectId);

    int removeById(Long id);
}