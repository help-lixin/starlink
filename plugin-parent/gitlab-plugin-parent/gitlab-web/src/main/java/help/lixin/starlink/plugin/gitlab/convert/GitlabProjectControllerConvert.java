package help.lixin.starlink.plugin.gitlab.convert;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.gitlab.dto.project.*;
import help.lixin.starlink.plugin.gitlab.request.project.*;
import help.lixin.starlink.request.EnvRequest;

@Mapper
public interface GitlabProjectControllerConvert {

    ProjectQueryDTO pageListConvert(ProjectPageListVO pageListVO);

    ProjectMemberUpdateDTO updateConvert(ProjectUpdateMemberVO projectUpdateMemberVO);

    ProjectPageListDTO pageListConvert(ProjectMemeberListVO projectMemeberListVO);

    ProjectGroupListDTO pageListConvert(ProjectGroupMemeberListVO projectGroupMemeberListVO);

    ProjectMemberSaveDTO saveConvert(ProjectMemberSaveVO projectMemberSaveVO);

    ProjectSaveDTO convert(ProjectSaveVO projectSaveVO);

    ProjectInfoDTO convert(EnvRequest envRequest);
}
