package help.lixin.starlink.plugin.rocketmq.api.dto.namesvr;

import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 6:35 下午
 * @Description
 */
public class NameSvrInfoDTO {

    private Boolean useVIPChannel;

    private List<String> namesvrAddrList;

    public Boolean getUseVIPChannel() {
        return useVIPChannel;
    }

    public void setUseVIPChannel(Boolean useVIPChannel) {
        this.useVIPChannel = useVIPChannel;
    }

    public List<String> getNamesvrAddrList() {
        return namesvrAddrList;
    }

    public void setNamesvrAddrList(List<String> namesvrAddrList) {
        this.namesvrAddrList = namesvrAddrList;
    }
}
