package help.lixin.starlink.plugin.ansible.convert;

import help.lixin.starlink.plugin.ansible.dto.CreateHostDTO;
import help.lixin.starlink.plugin.ansible.dto.HostPageListDTO;
import help.lixin.starlink.plugin.ansible.request.CreateHostVO;
import help.lixin.starlink.plugin.ansible.request.HostPageListVO;
import org.mapstruct.Mapper;

@Mapper
public interface AnsibleHostControllerConvert {

    HostPageListDTO convert(HostPageListVO hostPageListVO);

    CreateHostDTO convert(CreateHostVO createHostVO);
}
