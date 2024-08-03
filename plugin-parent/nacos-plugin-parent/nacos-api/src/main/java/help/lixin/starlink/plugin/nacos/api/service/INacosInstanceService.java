package help.lixin.starlink.plugin.nacos.api.service;

import java.util.List;

import help.lixin.starlink.plugin.nacos.api.dto.instance.InstanceDetailResponse;
import help.lixin.starlink.plugin.nacos.api.dto.instance.InstanceResponse;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceBeat;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceDetail;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceInfo;
import help.lixin.starlink.plugin.nacos.api.model.instance.NacosInstanceList;

public interface INacosInstanceService {

    /**
     * @Param nacosInstanceInfo :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:55 下午
     * @Return: java.lang.Boolean
     * @Description 注册实例
     */
    Boolean registerInstance(NacosInstanceInfo nacosInstanceInfo);

    /**
     * @Param nacosInstanceInfo :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:56 下午
     * @Return: java.lang.Boolean
     * @Description 更新实例
     */
    Boolean updateInstance(NacosInstanceInfo nacosInstanceInfo);

    /**
     * @Param nacosInstanceBeat :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:56 下午
     * @Return: java.lang.String
     * @Description 发布beat
     */
    String beat(NacosInstanceBeat nacosInstanceBeat);

    /**
     * @Param nacosInstanceInfo :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:56 下午
     * @Return: java.lang.String
     * @Description 清除实例
     */
    String destoryInstance(NacosInstanceInfo nacosInstanceInfo);

    /**
     * @Param nameSpaceId :
     * @Author: 伍岳林
     * @Date: 2023/7/20 5:11 下午
     * @Return: java.util.List<help.lixin.starlink.plugin.nacos.api.dto.instance.InstanceDetailResponse>
     * @Description 查询缓存列表
     */
    List<InstanceDetailResponse> cacheList();

    InstanceDetailResponse cacheDetail(String nameSpaceId);

    /**
     * @Param nacosInstanceList :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:56 下午
     * @Return: java.lang.String
     * @Description 查询实例列表
     * @return
     */
    InstanceResponse instanceList(NacosInstanceList nacosInstanceList);

    /**
     * @Param nacosInstanceDetail :
     * @Author: 伍岳林
     * @Date: 2023/7/19 3:56 下午
     * @Return: java.lang.String
     * @Description 查询实例详情
     * @return
     */
    InstanceDetailResponse instanceDetail(NacosInstanceDetail nacosInstanceDetail);

}
