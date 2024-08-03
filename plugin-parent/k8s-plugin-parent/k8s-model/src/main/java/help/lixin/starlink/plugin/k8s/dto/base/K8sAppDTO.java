package help.lixin.starlink.plugin.k8s.dto.base;

/**
 * @Author: 伍岳林
 * @Date: 2024/5/17 下午5:46
 * @Description
 */
public class K8sAppDTO extends K8sBaseInfoDTO {

    private Long id;
    private String jsonBody;
    private String kind;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJsonBody() {
        return jsonBody;
    }

    public void setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }
}
