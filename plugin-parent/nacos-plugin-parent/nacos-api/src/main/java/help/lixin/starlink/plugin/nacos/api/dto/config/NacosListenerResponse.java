package help.lixin.starlink.plugin.nacos.api.dto.config;

import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/17 8:18 下午
 * @Description
 */
public class NacosListenerResponse {

    private int collectStatus;

    private Map<String, String> lisentersGroupkeyStatus;

    public int getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(int collectStatus) {
        this.collectStatus = collectStatus;
    }

    public Map<String, String> getLisentersGroupkeyStatus() {
        return lisentersGroupkeyStatus;
    }

    public void setLisentersGroupkeyStatus(Map<String, String> lisentersGroupkeyStatus) {
        this.lisentersGroupkeyStatus = lisentersGroupkeyStatus;
    }
}
