package help.lixin.starlink.plugin.k8s.service.subscribe;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import help.lixin.starlink.plugin.credential.adapter.event.namespace.NameSpaceCreateEvent;
import help.lixin.starlink.plugin.credential.adapter.event.namespace.NameSpaceDeleteEvent;
import help.lixin.starlink.plugin.credential.domain.namespace.NameSpaceCreate;
import help.lixin.starlink.plugin.credential.domain.namespace.NameSpaceDelete;
import help.lixin.starlink.plugin.k8s.domain.KubernetesNameSpace;
import help.lixin.starlink.plugin.k8s.mapper.KubernetesNameSpaceMapper;
import org.springframework.context.event.EventListener;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/22 下午3:53
 * @Description
 */
public class K8sNameSpaceCreateEventSubscribeService {

    private KubernetesNameSpaceMapper kubernetesNameSpaceMapper;

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


        KubernetesNameSpace kubernetesNameSpace = new KubernetesNameSpace();
        kubernetesNameSpace.setInstanceCode(instanceCode);
        kubernetesNameSpace.setName(nameSpaceCreate.getNameSpace());
        kubernetesNameSpace.setCreateTime(nameSpaceCreate.getCreateTime());

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("instance_code", kubernetesNameSpace.getInstanceCode());
        queryWrapper.eq("name", kubernetesNameSpace.getName());
        KubernetesNameSpace nameSpace = kubernetesNameSpaceMapper.selectOne(queryWrapper);


        if (nameSpace == null) {
            kubernetesNameSpaceMapper.insertSelective(kubernetesNameSpace);
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

        KubernetesNameSpace kubernetesNameSpace = new KubernetesNameSpace();
        kubernetesNameSpace.setInstanceCode(nameSpaceDelete.getInstanceCode());
        kubernetesNameSpace.setName(nameSpaceDelete.getNameSpace());

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("instance_code",kubernetesNameSpace.getInstanceCode());
        queryWrapper.eq("name",kubernetesNameSpace.getName());
        KubernetesNameSpace nameSpace = kubernetesNameSpaceMapper.selectOne(queryWrapper);

        if(nameSpace != null){
            nameSpace.setIsDel(1);
            kubernetesNameSpaceMapper.updateById(nameSpace);
        }
    }

    public K8sNameSpaceCreateEventSubscribeService(KubernetesNameSpaceMapper kubernetesNameSpaceMapper) {
        this.kubernetesNameSpaceMapper = kubernetesNameSpaceMapper;
    }
}
