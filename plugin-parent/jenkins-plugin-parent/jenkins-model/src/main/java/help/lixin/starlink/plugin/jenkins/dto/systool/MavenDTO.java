package help.lixin.starlink.plugin.jenkins.dto.systool;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MavenDTO {
    @JsonProperty("tool")
    private List<ToolDTO> tool = new ArrayList<>(0);

    public List<ToolDTO> getTool() {
        return tool;
    }

    public void setTool(List<ToolDTO> tool) {
        this.tool = tool;
    }
}