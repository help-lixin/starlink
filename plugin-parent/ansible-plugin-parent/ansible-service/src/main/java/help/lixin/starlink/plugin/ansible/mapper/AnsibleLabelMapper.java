package help.lixin.starlink.plugin.ansible.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.ansible.domain.AnsibleLabel;
import help.lixin.starlink.plugin.ansible.dto.LabelPageListDTO;

import java.util.List;

public interface AnsibleLabelMapper extends BaseMapper<AnsibleLabel> {

    int insertSelective(AnsibleLabel record);

    AnsibleLabel selectByLabelKey(String key);

    List<AnsibleLabel> pageList(LabelPageListDTO labelPageListDTO);

    List<AnsibleLabel> selectAll();

    int removeById(Long id);
}