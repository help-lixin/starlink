package help.lixin.starlink.plugin.rocketmq.api.service;

import help.lixin.starlink.plugin.rocketmq.api.dto.namesvr.NameSvrInfoDTO;

public interface INameSvrAddrService {

    /**
     * @Author: 伍岳林
     * @Date: 2023/8/31 6:40 下午
     * @Return: help.lixin.rocketmq.api.dto.namesvr.NameSvrInfoDTO
     * @Description 地址列表
    */
    NameSvrInfoDTO list();

    /**
     * @Param useVIPChannel :
     * @Author: 伍岳林
     * @Date: 2023/8/31 6:40 下午
     * @Return: java.lang.Boolean
     * @Description 更新VIP通道方法
    */
    Boolean updateIsVIPChannel(Boolean useVIPChannel);

    /**
     * @Param nameSvrAddrList :
     * @Author: 伍岳林
     * @Date: 2023/8/31 6:41 下午
     * @Return: java.lang.Boolean
     * @Description 根据当前地址列表更新namesvr
    */
    Boolean updateNameSvrAddr(String nameSvrAddrList);
}
