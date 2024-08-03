package help.lixin.starlink.plugin.ansible.convert;

import help.lixin.starlink.plugin.ansible.domain.AnsibleHostManage;
import help.lixin.starlink.plugin.ansible.dto.CreateHostDTO;
import org.mapstruct.Mapper;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/26 下午2:50
 * @Description
 */
@Mapper
public interface AnsibleHostServiceConvert {
    AnsibleHostManage convert(CreateHostDTO createHostDTO);
}
