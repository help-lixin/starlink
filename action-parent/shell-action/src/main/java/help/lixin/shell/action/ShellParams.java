package help.lixin.shell.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShellParams {
    private List<String> cmds = new ArrayList<>(0);
    private Map<String, Object> others = new HashMap<>();

    public List<String> getCmds() {
        return cmds;
    }

    public void setCmds(List<String> cmds) {
        this.cmds = cmds;
    }

    public Map<String, Object> getOthers() {
        return others;
    }

    public void setOthers(Map<String, Object> others) {
        this.others = others;
    }
}
