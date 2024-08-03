package help.lixin.starlink.plugin.jenkins.dto.systool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GitDTO {
    @JsonProperty("tool")
    private List<GitToolInfoDTO> tool = new ArrayList<>(0);

    public List<GitToolInfoDTO> getTool() {
        return tool;
    }

    public void setTool(List<GitToolInfoDTO> tool) {
        this.tool = tool;
    }
}