package help.lixin.starlink.plugin.gitlab.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.gitlab.dto.project.ProjectInfoDTO;
import help.lixin.starlink.plugin.gitlab.dto.project.ProjectQueryDTO;
import help.lixin.starlink.plugin.gitlab.domain.GitlabProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GitlabProjectMapper extends BaseMapper<GitlabProject> {
    int deleteByPrimaryKey(Long id);

    int insertSelective(GitlabProject record);

    GitlabProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GitlabProject record);

    int updateByPrimaryKey(GitlabProject record);

    /** =============== custom sql =============== **/
    GitlabProject queryProjectByProjectName(@Param("projectName") String projectName,@Param("instanceCode") String instanceCode);

    int removeProject(@Param("projectId") Long projectId);

    List<GitlabProject> queryProjects(ProjectQueryDTO projectQueryDTO);

    List<GitlabProject> queryProjectListByInstanceCode(String instanceCode);

    List<GitlabProject> queryProjectByGitlabProjectIds(@Param("gitlabProjectIds") List<Long> gitlabProjectIds);

    List<GitlabProject> queryUserProjectByProjectIds(@Param("gitlabProjectIds") List<Long> gitlabProjectIds);

    List<GitlabProject> queryGroupProjectByProjectIds(@Param("gitlabProjectIds") List<Long> gitlabProjectIds);

    GitlabProject queryByGitlabProjectId(ProjectInfoDTO gitlabProjectId);

}