package help.lixin.starlink.plugin.ansible.service;

import help.lixin.starlink.plugin.ansible.domain.AnsibleLabel;
import help.lixin.starlink.plugin.ansible.dto.CreateLabelDTO;
import help.lixin.starlink.plugin.ansible.dto.LabelPageListDTO;
import help.lixin.starlink.plugin.ansible.dto.UpdateInventoryDTO;
import help.lixin.response.PageResponse;

import java.util.List;

public interface IAnsibleLabelService {

    PageResponse<AnsibleLabel> pageList(LabelPageListDTO pageListDTO);

    Boolean createLabel(CreateLabelDTO createLabelDTO);

    Boolean updateInventory(UpdateInventoryDTO updateInventoryDTO);

    Boolean changeStatus(Long id, Integer status, String createBy);

    Boolean checkKey(String labelKey);

    List<String> queryLabelDetail(Long id);

    Boolean removeLabel(Long labelId);
}
