package help.lixin.starlink.plugin.harbor.api.dto.repository;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author: 伍岳林
 * @Date: 2024/1/17 下午4:17
 * @Description
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdditionLinksDTO {
    @JsonProperty("build_history")
    private BuildHistoryDTO buildHistory;

    public BuildHistoryDTO getBuildHistory() {
        return buildHistory;
    }

    public void setBuildHistory(BuildHistoryDTO buildHistory) {
        this.buildHistory = buildHistory;
    }
}
