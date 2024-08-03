package help.lixin.starlink.plugin.jsch.convert;

import org.mapstruct.Mapper;

import help.lixin.starlink.plugin.jsch.domain.SshLabel;
import help.lixin.starlink.plugin.jsch.dto.SaveSshLabelDTO;

@Mapper
public interface SshLabelServiceConvert {

    SshLabel convert(SaveSshLabelDTO saveSshLabelDTO);
}
