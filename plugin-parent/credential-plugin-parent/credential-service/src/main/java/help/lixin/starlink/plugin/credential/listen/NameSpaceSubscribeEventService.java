package help.lixin.starlink.plugin.credential.listen;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import help.lixin.starlink.plugin.credential.adapter.event.namespace.NameSpaceCreateEvent;
import help.lixin.starlink.plugin.credential.adapter.event.namespace.NameSpaceDeleteEvent;
import help.lixin.starlink.plugin.credential.domain.SysCredentialNamespaces;
import help.lixin.starlink.plugin.credential.domain.namespace.NameSpaceCreate;
import help.lixin.starlink.plugin.credential.domain.namespace.NameSpaceDelete;
import help.lixin.starlink.plugin.credential.mapper.SysCredentialNamespacesMapper;
import org.springframework.context.event.EventListener;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/22 下午3:53
 * @Description
 */
public class NameSpaceSubscribeEventService {

    private SysCredentialNamespacesMapper sysCredentialNamespacesMapper;

    /**
     * @Param event :
     * @Author: 伍岳林
     * @Date: 2024/5/23 下午5:14
     * @Return: void
     * @Description 新增命名空间监听
    */
    @EventListener({NameSpaceCreateEvent.class})
    public void handlerCreateEvent(NameSpaceCreateEvent event) {
        NameSpaceCreate nameSpaceCreate = event.getNameSpaceCreateEvent();
        String instanceCode = nameSpaceCreate.getInstanceCode();

        SysCredentialNamespaces sysCredentialNamespaces = new SysCredentialNamespaces();
        sysCredentialNamespaces.setInstanceCode(nameSpaceCreate.getInstanceCode());
        sysCredentialNamespaces.setNameSpace(nameSpaceCreate.getNameSpace());
        sysCredentialNamespaces.setCreateTime(nameSpaceCreate.getCreateTime());

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("instance_code", instanceCode);
        queryWrapper.eq("name_space", sysCredentialNamespaces.getNameSpace());
        SysCredentialNamespaces credentialNamespaces = sysCredentialNamespacesMapper.selectOne(queryWrapper);


        if (credentialNamespaces == null){
            sysCredentialNamespacesMapper.insertSelective(sysCredentialNamespaces);
        }

    }

    /**
     * @Param event :
     * @Author: 伍岳林
     * @Date: 2024/5/23 下午5:15
     * @Return: void
     * @Description 删除命名空间监听
    */
    @EventListener({NameSpaceDeleteEvent.class})
    public void handlerDeleteEvent(NameSpaceDeleteEvent event) {
        NameSpaceDelete nameSpaceDelete = event.getNameSpaceDelete();

        SysCredentialNamespaces sysCredentialNamespaces = new SysCredentialNamespaces();
        sysCredentialNamespaces.setInstanceCode(nameSpaceDelete.getInstanceCode());
        sysCredentialNamespaces.setNameSpace(nameSpaceDelete.getNameSpace());

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("instance_code",sysCredentialNamespaces.getInstanceCode());
        queryWrapper.eq("name_space",sysCredentialNamespaces.getNameSpace());
        SysCredentialNamespaces credentialNamespaces = sysCredentialNamespacesMapper.selectOne(queryWrapper);

        if(credentialNamespaces != null){
            credentialNamespaces.setIsDel(1);
            sysCredentialNamespacesMapper.updateById(credentialNamespaces);
        }
    }

    public NameSpaceSubscribeEventService(SysCredentialNamespacesMapper sysCredentialNamespacesMapper) {
        this.sysCredentialNamespacesMapper = sysCredentialNamespacesMapper;
    }
}
