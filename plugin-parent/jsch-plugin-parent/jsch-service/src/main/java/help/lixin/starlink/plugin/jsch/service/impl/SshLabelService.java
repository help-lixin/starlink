package help.lixin.starlink.plugin.jsch.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import help.lixin.exception.ServiceException;
import help.lixin.response.PageResponse;
import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.starlink.plugin.jsch.convert.SshLabelServiceConvert;
import help.lixin.starlink.plugin.jsch.domain.SshHosts;
import help.lixin.starlink.plugin.jsch.domain.SshLabel;
import help.lixin.starlink.plugin.jsch.dto.PageListSshLabelDTO;
import help.lixin.starlink.plugin.jsch.dto.SaveSshLabelDTO;
import help.lixin.starlink.plugin.jsch.mapper.SshHostsMapper;
import help.lixin.starlink.plugin.jsch.mapper.SshLabelMapper;
import help.lixin.starlink.plugin.jsch.service.ISshLabelService;

/**
 * @Author: 伍岳林
 * @Date: 2024/2/23 上午11:26
 * @Description
 */
public class SshLabelService implements ISshLabelService {

    private SshLabelMapper sshLabelMapper;
    private SshHostsMapper sshHostsMapper;
    private QueryTemplate queryTemplate;

    @Override
    public List<String> queryInfo(Long id) {
        return sshHostsMapper.selectCheckById(id);
    }

    @Override
    public PageResponse pageList(PageListSshLabelDTO pageListSshLabelDTO) {
        return queryTemplate.execute(pageListSshLabelDTO, () -> {
            sshLabelMapper.pageList(pageListSshLabelDTO);
        });
    }

    @Override
    public List<SshLabel> getAll() {
        QueryWrapper<SshLabel> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        return sshLabelMapper.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean save(SaveSshLabelDTO saveSshLabelDTO) {
        Long id = saveSshLabelDTO.getId();

        SshLabel sshLabelDB = sshLabelMapper.selectByLabelKey(saveSshLabelDTO.getLabelKey());
        if (id == null && sshLabelDB == null) {
            SshLabelServiceConvert mapper = Mappers.getMapper(SshLabelServiceConvert.class);
            SshLabel sshLabel = mapper.convert(saveSshLabelDTO);
            sshLabel.setIsDel(0);
            sshLabelMapper.insertSelective(sshLabel);
            createInventory(saveSshLabelDTO.getHosts(), sshLabel);
        } else {
            if (sshLabelDB.getId() != id) {
                throw new ServiceException("该数据已存在,请确认后重试");
            }
            SshLabel sshLabel = getSSHLabel(id);
            sshLabel.setLabelKey(saveSshLabelDTO.getLabelKey());
            sshLabel.setLabelName(saveSshLabelDTO.getLabelName());
            sshLabel.setStatus(saveSshLabelDTO.getStatus());
            sshLabel.setUpdateBy(saveSshLabelDTO.getCreateBy());
            sshLabelMapper.updateById(sshLabel);
            createInventory(saveSshLabelDTO.getHosts(), sshLabel);
        }

        return true;
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean changeStatus(Long id, Integer status, String updateBy) {
        SshLabel sshLabel = getSSHLabel(id);
        sshLabel.setStatus(status);
        sshLabel.setUpdateBy(updateBy);
        return sshLabelMapper.updateById(sshLabel) > 0;
    }

    @Override
    public Boolean checkLabelKey(String labelKey) {
        return sshLabelMapper.selectByLabelKey(labelKey) != null;
    }

    @Override
    public Boolean removeSsh(Long id) {
        SshLabel sshLabel = sshLabelMapper.selectById(id);
        if (sshLabel == null) {
            throw new ServiceException("该id不存在");
        }

        return sshLabelMapper.removeSsh(id) > 0;
    }

    private SshLabel getSSHLabel(Long id) {
        SshLabel sshLabel = sshLabelMapper.selectById(id);
        if (sshLabel == null) {
            throw new ServiceException("该id不存在");
        }
        return sshLabel;
    }

    private void createInventory(List<String> hosts, SshLabel SSHLabel) {
        List<SshHosts> sshHostsList = new ArrayList<>();
        hosts.forEach(i -> {
            SshHosts sshHosts = new SshHosts();
            Long id = SSHLabel.getId();
            sshHosts.setSshLableId(id);
            sshHosts.setSshInstanceCode(i);
            sshHostsList.add(sshHosts);
        });
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("ssh_lable_id", SSHLabel.getId());
        sshHostsMapper.delete(queryWrapper);
        if (!CollectionUtils.isEmpty(sshHostsList)) {
            sshHostsMapper.insertList(sshHostsList);
        }
    }

    public SshLabelService(SshLabelMapper sshLabelMapper, SshHostsMapper sshHostsMapper, QueryTemplate queryTemplate) {
        this.sshLabelMapper = sshLabelMapper;
        this.sshHostsMapper = sshHostsMapper;
        this.queryTemplate = queryTemplate;
    }

}
