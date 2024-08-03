package help.lixin.starlink.plugin.ansible.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import help.lixin.starlink.plugin.ansible.domain.AnsibleHostManage;
import help.lixin.starlink.plugin.ansible.dto.HostPageListDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnsibleHostManageMapper extends BaseMapper<AnsibleHostManage> {

    int insertSelective(AnsibleHostManage record);

    List<AnsibleHostManage> pageList(HostPageListDTO hostPageListDTO);

    List<AnsibleHostManage> selectAll();

    List<String> selectAllInstanceCode();

    AnsibleHostManage selectByServerName(@Param("serverName") String serverName,@Param("sshInstanceCode") String sshInstanceCode);

    AnsibleHostManage selectByInstanceCode(String sshInstanceCode);

    int removeById(Long id);
}