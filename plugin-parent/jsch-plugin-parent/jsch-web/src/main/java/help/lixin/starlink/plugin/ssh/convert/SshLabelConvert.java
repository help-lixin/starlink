package help.lixin.starlink.plugin.ssh.convert;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.ssh.request.PageListSshLabelVO;
import help.lixin.starlink.plugin.ssh.request.SaveSshLabelVO;
import help.lixin.starlink.plugin.jsch.dto.PageListSshLabelDTO;
import help.lixin.starlink.plugin.jsch.dto.SaveSshLabelDTO;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/23 下午2:40
 * @Description
 */
@Mapper
public interface SshLabelConvert {

    PageListSshLabelDTO convert(PageListSshLabelVO pageListSshLabelVO);

    SaveSshLabelDTO convert(SaveSshLabelVO saveSshLabelVO);
}
