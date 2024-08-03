package help.lixin.starlink.plugin.jsch.mapper;

import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import help.lixin.starlink.plugin.jsch.domain.SshHosts;

public interface SshHostsMapper extends BaseMapper<SshHosts> {

    int insertSelective(SshHosts sshHosts);

    int insertList(List<SshHosts> sshHostsList);

    List<String> selectCheckById(Long labelId);

    List<SshHosts> selectSshHostListByLabel(@Param("lables") Set<String> lables);
}