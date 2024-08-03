package help.lixin.starlink.plugin.nacos.api.dto.instance;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/19 6:50 下午
 * @Description
 */
public class InstanceResponse {
    private String name;

    private String groupName;

    private String clusters;

    private long cacheMillis = 1000L;

    private List<InstanceDetailResponse> hosts = new ArrayList<>();

    private long lastRefTime = 0L;

    private String checksum = "";

    private Boolean allIPs = false;

    private Boolean reachProtectionThreshold = false;

    private Boolean valid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getClusters() {
        return clusters;
    }

    public void setClusters(String clusters) {
        this.clusters = clusters;
    }

    public long getCacheMillis() {
        return cacheMillis;
    }

    public void setCacheMillis(long cacheMillis) {
        this.cacheMillis = cacheMillis;
    }

    public List<InstanceDetailResponse> getHosts() {
        return hosts;
    }

    public void setHosts(List<InstanceDetailResponse> hosts) {
        this.hosts = hosts;
    }

    public long getLastRefTime() {
        return lastRefTime;
    }

    public void setLastRefTime(long lastRefTime) {
        this.lastRefTime = lastRefTime;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }

    public Boolean getAllIPs() {
        return allIPs;
    }

    public void setAllIPs(Boolean allIPs) {
        this.allIPs = allIPs;
    }

    public Boolean getReachProtectionThreshold() {
        return reachProtectionThreshold;
    }

    public void setReachProtectionThreshold(Boolean reachProtectionThreshold) {
        this.reachProtectionThreshold = reachProtectionThreshold;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }
}
