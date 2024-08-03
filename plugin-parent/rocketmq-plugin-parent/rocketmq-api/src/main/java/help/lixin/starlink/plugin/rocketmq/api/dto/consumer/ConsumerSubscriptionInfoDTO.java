package help.lixin.starlink.plugin.rocketmq.api.dto.consumer;

import java.util.Set;

/**
 * @Author: 伍岳林
 * @Date: 2023/8/31 2:52 下午
 * @Description
 */
public class ConsumerSubscriptionInfoDTO {

    private Boolean classFilterMode;
    private String topic;
    private String subString;
    private Set<String> tagsSet;
    private Set<String> codeSet;
    private Long subVersion;
    private String filterClassSource;

    public Boolean getClassFilterMode() {
        return classFilterMode;
    }

    public void setClassFilterMode(Boolean classFilterMode) {
        this.classFilterMode = classFilterMode;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getSubString() {
        return subString;
    }

    public void setSubString(String subString) {
        this.subString = subString;
    }

    public Set<String> getTagsSet() {
        return tagsSet;
    }

    public void setTagsSet(Set<String> tagsSet) {
        this.tagsSet = tagsSet;
    }

    public Set<String> getCodeSet() {
        return codeSet;
    }

    public void setCodeSet(Set<String> codeSet) {
        this.codeSet = codeSet;
    }

    public Long getSubVersion() {
        return subVersion;
    }

    public void setSubVersion(Long subVersion) {
        this.subVersion = subVersion;
    }

    public String getFilterClassSource() {
        return filterClassSource;
    }

    public void setFilterClassSource(String filterClassSource) {
        this.filterClassSource = filterClassSource;
    }
}
