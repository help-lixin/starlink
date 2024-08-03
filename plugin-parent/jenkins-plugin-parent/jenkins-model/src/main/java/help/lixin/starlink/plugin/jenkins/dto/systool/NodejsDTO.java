package help.lixin.starlink.plugin.jenkins.dto.systool;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 伍岳林
 * @Date: 2023/12/19 3:06 下午
 * @Description
 */
public class NodejsDTO {

    @JsonProperty("tool")
    private List<ToolDTO> tool = new ArrayList<>(0);

    public List<ToolDTO> getTool() {
        return tool;
    }

    public void setTool(List<ToolDTO> tool) {
        this.tool = tool;
    }
}
