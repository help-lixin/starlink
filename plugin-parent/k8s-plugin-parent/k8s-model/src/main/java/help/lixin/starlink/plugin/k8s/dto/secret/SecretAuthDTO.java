package help.lixin.starlink.plugin.k8s.dto.secret;

import java.util.Map;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/31 下午4:44
 * @Description
 */
public class SecretAuthDTO {

    private Map<String,SecretUserNamePasswordDTO> auths;

    public Map<String, SecretUserNamePasswordDTO> getAuths() {
        return auths;
    }

    public void setAuths(Map<String, SecretUserNamePasswordDTO> auths) {
        this.auths = auths;
    }
}
