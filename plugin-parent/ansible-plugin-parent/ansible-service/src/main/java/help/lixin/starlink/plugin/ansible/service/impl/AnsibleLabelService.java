package help.lixin.starlink.plugin.ansible.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.ansible.domain.AnsibleInventory;
import help.lixin.starlink.plugin.ansible.domain.AnsibleLabel;
import help.lixin.starlink.plugin.ansible.dto.CreateLabelDTO;
import help.lixin.starlink.plugin.ansible.dto.LabelPageListDTO;
import help.lixin.starlink.plugin.ansible.dto.UpdateInventoryDTO;
import help.lixin.starlink.plugin.ansible.mapper.AnsibleInventoryMapper;
import help.lixin.starlink.plugin.ansible.mapper.AnsibleLabelMapper;
import help.lixin.starlink.plugin.ansible.mq.provider.DeleteAnsibleHostsHandler;
import help.lixin.starlink.plugin.ansible.mq.provider.UpdateAnsibleHostsHandler;
import help.lixin.starlink.plugin.ansible.service.IAnsibleLabelService;
import help.lixin.response.PageResponse;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/26 上午11:28
 * @Description
 */
public class AnsibleLabelService implements IAnsibleLabelService {

    private AnsibleLabelMapper ansibleLabelMapper;
    private AnsibleInventoryMapper ansibleInventoryMapper;
    private UpdateAnsibleHostsHandler updateAnsibleHostsHandler;
    private DeleteAnsibleHostsHandler deleteAnsibleHostsHandler;

    private QueryTemplate queryTemplate;

    @Override
    public PageResponse<AnsibleLabel> pageList(LabelPageListDTO pageListDTO) {
        return queryTemplate.execute(pageListDTO,()->{
            ansibleLabelMapper.pageList(pageListDTO);
        });
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean createLabel(CreateLabelDTO createLabelDTO) {
        AnsibleLabel existLabel = ansibleLabelMapper.selectByLabelKey(createLabelDTO.getLabelKey());
        if (existLabel != null){
            throw new ServiceException("存在相同的标签key，请确认后重试");
        }
        AnsibleLabel ansibleLabel = new AnsibleLabel();
        ansibleLabel.setLabelKey(createLabelDTO.getLabelKey());
        ansibleLabel.setLabelName(createLabelDTO.getLabelName());
        ansibleLabel.setCreateBy(createLabelDTO.getCreateBy());
        ansibleLabel.setUpdateBy(createLabelDTO.getCreateBy());
        ansibleLabelMapper.insertSelective(ansibleLabel);
        createInventory(createLabelDTO.getInventorys(), ansibleLabel);
        updateAnsibleHostsHandler.updateHosts(ansibleLabel.getId(),ansibleLabel.getLabelKey(),createLabelDTO.getInventorys());
        return true;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean updateInventory(UpdateInventoryDTO updateInventoryDTO) {
        AnsibleLabel ansibleLabel = ansibleLabelMapper.selectById(updateInventoryDTO.getId());
        if (ansibleLabel == null){
            throw new ServiceException("该id不存在，请确认后重试");
        }
        createInventory(updateInventoryDTO.getInventorys(),ansibleLabel);

        if (!ansibleLabel.getLabelName().equals(updateInventoryDTO.getLabelName())) {
            ansibleLabel.setLabelName(updateInventoryDTO.getLabelName());
            ansibleLabelMapper.updateById(ansibleLabel);
        }

        updateAnsibleHostsHandler.updateHosts(ansibleLabel.getId(),ansibleLabel.getLabelKey(),updateInventoryDTO.getInventorys());
        return true;
    }

    private void createInventory(List<String> inventorys, AnsibleLabel ansibleLabel) {
        List<AnsibleInventory> ansibleInventoryList = new ArrayList<>();
        inventorys.forEach(i ->{
            AnsibleInventory ansibleInventory = new AnsibleInventory();
            Long id = ansibleLabel.getId();
            ansibleInventory.setAnsibleLableId(id);
            ansibleInventory.setSshInstanceCode(i);
            ansibleInventoryList.add(ansibleInventory);
        });
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ansible_lable_id", ansibleLabel.getId());
        ansibleInventoryMapper.delete(queryWrapper);
        if (!CollectionUtils.isEmpty(ansibleInventoryList)) {
            ansibleInventoryMapper.insertList(ansibleInventoryList);
        }
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean changeStatus(Long id, Integer status, String createBy) {
        AnsibleLabel ansibleLabel = ansibleLabelMapper.selectById(id);
        if (ansibleLabel == null){
            throw new ServiceException("该id不存在，请确认后重试");
        }
        ansibleLabel.setStatus(status);
        ansibleLabel.setUpdateTime(new Date());
        return ansibleLabelMapper.updateById(ansibleLabel) > 0;

    }

    @Override
    public Boolean checkKey(String labelKey) {
        return ansibleLabelMapper.selectByLabelKey(labelKey) != null;
    }

    @Override
    public List<String> queryLabelDetail(Long id) {
        return ansibleInventoryMapper.selectByAnsibleLabelId(id);
    }

    @Override
    public Boolean removeLabel(Long labelId) {
        AnsibleLabel ansibleLabel = ansibleLabelMapper.selectById(labelId);
        if (ansibleLabel == null){
            throw new ServiceException("该id不存在");
        }
        boolean result = ansibleLabelMapper.removeById(labelId) > 0;
        deleteAnsibleHostsHandler.deleteHosts(labelId,ansibleLabel.getLabelKey());
        return result;
    }

    public AnsibleLabelService(AnsibleLabelMapper ansibleLabelMapper,
                               AnsibleInventoryMapper ansibleInventoryMapper,
                               UpdateAnsibleHostsHandler updateAnsibleHostsHandler,
                               DeleteAnsibleHostsHandler deleteAnsibleHostsHandler,
                               QueryTemplate queryTemplate) {
        this.ansibleLabelMapper = ansibleLabelMapper;
        this.ansibleInventoryMapper = ansibleInventoryMapper;
        this.updateAnsibleHostsHandler = updateAnsibleHostsHandler;
        this.deleteAnsibleHostsHandler = deleteAnsibleHostsHandler;
        this.queryTemplate = queryTemplate;
    }
}
