package help.lixin.admin.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractStartPipelineParam implements Serializable {
    private String businessKey;
    private Map<String, Object> vars = new HashMap<>();

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public Map<String, Object> getVars() {
        return vars;
    }

    public void setVars(Map<String, Object> vars) {
        if (null != vars) {
            this.vars = vars;
        }
    }
}
