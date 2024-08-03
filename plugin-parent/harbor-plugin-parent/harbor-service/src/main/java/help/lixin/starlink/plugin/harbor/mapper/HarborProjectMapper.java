package help.lixin.starlink.plugin.harbor.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import help.lixin.starlink.plugin.harbor.domain.HarborProject;
import help.lixin.starlink.plugin.harbor.dto.PageListDTO;

public interface HarborProjectMapper extends BaseMapper<HarborProject> {

    HarborProject selectByPrimaryKey(Long id);

    int insertSelective(HarborProject harborProject);

    List<HarborProject> queryProjectByHarborProjectIds(List<Long> ids);

    HarborProject selectByNullHarborProjectId(HarborProject harborProject);

    List<HarborProject> queryByProjectName(@Param("projectName") String projectName,
        @Param("instanceCode") String instanceCode);

    List<HarborProject> pageList(PageListDTO pageListDTO);

    int removeById(Long id);

    HarborProject checkProjectName(@Param("projectName") String projectName,
        @Param("instanceCode") String instanceCode);

}