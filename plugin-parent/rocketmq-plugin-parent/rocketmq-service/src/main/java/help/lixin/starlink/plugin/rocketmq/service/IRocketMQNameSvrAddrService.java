package help.lixin.starlink.plugin.rocketmq.service;

import help.lixin.starlink.plugin.rocketmq.api.dto.namesvr.NameSvrInfoDTO;

public interface IRocketMQNameSvrAddrService {

    /**
     * @Author: 伍岳林
     * @Date: 2023/8/31 6:40 下午
     * @Return: help.lixin.rocketmq.api.dto.namesvr.NameSvrInfoDTO
     * @Description 地址列表
     */
    NameSvrInfoDTO list(String instanceName);

    /**
     * @Param useVIPChannel :
     * @Author: 伍岳林
     * @Date: 2023/8/31 6:40 下午
     * @Return: java.lang.Boolean
     * @Description 更新VIP通道方法
     */
    Boolean updateIsVIPChannel(Boolean useVIPChannel, String instanceName);

    /**
     * @Param nameSvrAddrList :
     * @Author: 伍岳林
     * @Date: 2023/8/31 6:41 下午
     * @Return: java.lang.Boolean
     * @Description 根据当前地址列表更新namesvr
     */
    Boolean updateNameSvrAddr(String nameSvrAddrList, String instanceName);
}
