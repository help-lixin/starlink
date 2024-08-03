package help.lixin.starlink.plugin.gitlab.convert;

import help.lixin.starlink.plugin.gitlab.domain.GitlabUserProject;
import help.lixin.starlink.plugin.gitlab.dto.project.ProjectMemberSaveDTO;
import org.mapstruct.Mapper;

@Mapper
public interface ProjectServiceConvert {

    GitlabUserProject convert(ProjectMemberSaveDTO projectMemberSaveDTO);
}
