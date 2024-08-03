package help.lixin.starlink.plugin.gitlab.convert;

import help.lixin.starlink.plugin.gitlab.convert.rule.GitlabRule;
import help.lixin.starlink.plugin.gitlab.dto.group.*;
import help.lixin.starlink.plugin.gitlab.request.group.*;
import org.mapstruct.Mapper;

@Mapper(uses = GitlabRule.class)
public interface GitlabGroupControllerConvert {

    GroupQueryDTO queryConvert(GroupQueryVO groupQueryVO);

    GroupSaveDTO saveConvert(GroupSaveVO groupSaveVO);

    GroupPageListDTO pageConvert(GroupPageListVO pageListVO);

    GroupMemberPageListDTO pageConvert(MemberPageListVO pageListVO);

    GroupMemberAddDTO addConvert(GroupAddMemberVO groupAddMemberVO);

    GroupMemberUpdateDTO updateConvert(GroupUpdateMemberVO groupUpdateMemberVO);
}
