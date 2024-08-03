package help.lixin.starlink.plugin.jsch.service;

import java.util.List;

import help.lixin.response.PageResponse;
import help.lixin.starlink.plugin.jsch.domain.SshLabel;
import help.lixin.starlink.plugin.jsch.dto.PageListSshLabelDTO;
import help.lixin.starlink.plugin.jsch.dto.SaveSshLabelDTO;

public interface ISshLabelService {

    List<String> queryInfo(Long id);

    PageResponse pageList(PageListSshLabelDTO pageListSshLabelDTO);

    List<SshLabel> getAll();

    Boolean save(SaveSshLabelDTO saveSshLabelDTO);

    Boolean changeStatus(Long id, Integer status, String updateBy);

    Boolean checkLabelKey(String labelKey);

    Boolean removeSsh(Long id);
}
