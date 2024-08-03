package help.lixin.starlink.plugin.gitlab.convert;

import help.lixin.starlink.plugin.gitlab.dto.repository.BranchDTO;
import org.gitlab4j.api.models.Branch;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/11/21 4:36 下午
 * @Description
 */
@Mapper
public interface RepositoryServiceConvert {

    List<BranchDTO> convert(List<Branch> branch);

}
