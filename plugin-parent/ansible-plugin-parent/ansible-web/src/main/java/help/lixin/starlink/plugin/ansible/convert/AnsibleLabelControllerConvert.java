package help.lixin.starlink.plugin.ansible.convert;

import help.lixin.starlink.plugin.ansible.dto.CreateLabelDTO;
import help.lixin.starlink.plugin.ansible.dto.LabelPageListDTO;
import help.lixin.starlink.plugin.ansible.dto.UpdateInventoryDTO;
import help.lixin.starlink.plugin.ansible.request.CreateLabelVO;
import help.lixin.starlink.plugin.ansible.request.LabelPageListVO;
import help.lixin.starlink.plugin.ansible.request.UpdateInventoryVO;
import org.mapstruct.Mapper;

@Mapper
public interface AnsibleLabelControllerConvert {

    CreateLabelDTO convert(CreateLabelVO createLabelVO);

    UpdateInventoryDTO convert(UpdateInventoryVO updateInventoryVO);

    LabelPageListDTO convert(LabelPageListVO labelPageListVO);
}
