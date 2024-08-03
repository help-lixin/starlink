package help.lixin.starlink.plugin.ansible.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.ansible.domain.AnsibleInventory;

import java.util.List;

public interface AnsibleInventoryMapper extends BaseMapper<AnsibleInventory> {

    int insertList(List<AnsibleInventory> records);

    int insertSelective(AnsibleInventory record);

    List<String> selectByAnsibleLabelId(Long id);
}