package help.lixin.starlink.plugin.jsch.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.jsch.domain.SshLabel;
import help.lixin.starlink.plugin.jsch.dto.PageListSshLabelDTO;

import java.util.List;

public interface SshLabelMapper extends BaseMapper<SshLabel> {

    int insertSelective(SshLabel record);

    List<SshLabel> pageList(PageListSshLabelDTO pageListSshLabelDTO);

    SshLabel selectByLabelKey(String labelKey);

    int removeSsh(Long id);
}