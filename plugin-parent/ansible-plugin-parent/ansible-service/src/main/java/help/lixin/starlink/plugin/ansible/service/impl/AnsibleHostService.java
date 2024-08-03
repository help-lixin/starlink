package help.lixin.starlink.plugin.ansible.service.impl;

import help.lixin.starlink.core.template.QueryTemplate;
import help.lixin.exception.ServiceException;
import help.lixin.starlink.plugin.ansible.convert.AnsibleHostServiceConvert;
import help.lixin.starlink.plugin.ansible.domain.AnsibleHostManage;
import help.lixin.starlink.plugin.ansible.dto.CreateHostDTO;
import help.lixin.starlink.plugin.ansible.dto.HostPageListDTO;
import help.lixin.starlink.plugin.ansible.mapper.AnsibleHostManageMapper;
import help.lixin.starlink.plugin.ansible.service.IAnsibleHostService;
import help.lixin.response.PageResponse;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/26 上午11:28
 * @Description
 */
public class AnsibleHostService implements IAnsibleHostService {

    private AnsibleHostManageMapper ansibleHostManageMapper;

    private QueryTemplate queryTemplate;

    @Override
    public PageResponse<AnsibleHostManage> pageList(HostPageListDTO hostPageListDTO) {
        return queryTemplate.execute(hostPageListDTO,()->{
            ansibleHostManageMapper.pageList(hostPageListDTO);
        });
    }

    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean saveHost(CreateHostDTO createHostDTO) {
        AnsibleHostManage ansibleHostManage = ansibleHostManageMapper.selectByServerName(createHostDTO.getServerName(),
                createHostDTO.getSshInstanceCode());
        Long id = createHostDTO.getId();
        if (id == null){
            if (ansibleHostManage != null){
                throw new ServiceException("存在相同的服务名，请确认后重试");
            }
            AnsibleHostManage onlyInstance = ansibleHostManageMapper.selectByInstanceCode(createHostDTO.getSshInstanceCode());
            if ( onlyInstance != null ){
                throw new ServiceException("存在相同的ansible实例，请确认后重试");
            }
            AnsibleHostServiceConvert mapper = Mappers.getMapper(AnsibleHostServiceConvert.class);
            AnsibleHostManage hostManage = mapper.convert(createHostDTO);
            hostManage.setUpdateBy(createHostDTO.getCreateBy());
            return ansibleHostManageMapper.insertSelective(hostManage) > 0;
        }else{
            if ( ansibleHostManage != null && !ansibleHostManage.getId().equals(id) ){
                throw new ServiceException("存在相同的服务名，请确认后重试");
            }
            AnsibleHostManage hostManage = ansibleHostManageMapper.selectById(id);
            if (hostManage == null){
                throw new ServiceException("该id不存在，请确认后重试");
            }
            AnsibleHostManage onlyInstance = ansibleHostManageMapper.selectByInstanceCode(createHostDTO.getSshInstanceCode());
            if ( onlyInstance != null && onlyInstance.getId() != id){
                throw new ServiceException("存在相同的ansible实例，请确认后重试");
            }
            hostManage.setAnsibleInventoryDir(createHostDTO.getAnsibleInventoryDir());
            hostManage.setServerName(createHostDTO.getServerName());
            hostManage.setSshInstanceCode(createHostDTO.getSshInstanceCode());
            hostManage.setStatus(createHostDTO.getStatus());
            hostManage.setCreateBy(createHostDTO.getCreateBy());
            hostManage.setUpdateBy(createHostDTO.getCreateBy());
            hostManage.setUpdateTime(new Date());
            return ansibleHostManageMapper.updateById(hostManage) > 0;
        }
    }


    @Override
    @Transactional(rollbackFor = {ServiceException.class})
    public Boolean changeStatus(Long id, Integer status, String createBy) {
        AnsibleHostManage ansibleHostManage = ansibleHostManageMapper.selectById(id);
        if (ansibleHostManage == null){
            throw new ServiceException("该id不存在，请确认后重试");
        }
        ansibleHostManage.setStatus(status);
        return ansibleHostManageMapper.updateById(ansibleHostManage) > 0;
    }

    @Override
    public Boolean checkServerName( String serverName, String sshInstanceCode) {
        return ansibleHostManageMapper.selectByServerName(serverName, sshInstanceCode) != null;
    }

    @Override
    public AnsibleHostManage queryDetail(Long id) {
        return ansibleHostManageMapper.selectById(id);
    }

    @Override
    public Boolean checkInstance(String sshInstanceCode) {
        return ansibleHostManageMapper.selectByInstanceCode(sshInstanceCode) != null;
    }

    @Override
    public Boolean removeHost(Long id) {
        AnsibleHostManage ansibleHostManage = ansibleHostManageMapper.selectById(id);
        if (ansibleHostManage == null){
            throw new ServiceException("该id不存在");
        }
        return ansibleHostManageMapper.removeById(id) > 0;
    }

    public AnsibleHostService(AnsibleHostManageMapper ansibleHostManageMapper, QueryTemplate queryTemplate) {
        this.ansibleHostManageMapper = ansibleHostManageMapper;
        this.queryTemplate = queryTemplate;
    }
}
