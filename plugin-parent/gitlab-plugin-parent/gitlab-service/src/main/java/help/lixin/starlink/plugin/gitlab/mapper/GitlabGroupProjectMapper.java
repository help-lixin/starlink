package help.lixin.starlink.plugin.gitlab.mapper;

import help.lixin.starlink.plugin.gitlab.domain.GitlabGroupProject;

public interface GitlabGroupProjectMapper {
    int deleteByPrimaryKey(Long id);

    int insert(GitlabGroupProject record);

    int insertSelective(GitlabGroupProject record);

    GitlabGroupProject selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(GitlabGroupProject record);

    int updateByPrimaryKey(GitlabGroupProject record);

    GitlabGroupProject queryProjectByProjectId(Long projectId);
}