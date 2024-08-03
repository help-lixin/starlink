package help.lixin.starlink.plugin.k8s.request.daemonset;

import javax.validation.constraints.NotBlank;

import help.lixin.starlink.plugin.k8s.request.base.BaseInfoVO;

/**
 * @Author: 伍岳林
 * @Date: 2024/6/21 下午5:24
 * @Description
 */
public class CreateDaemonSetVO extends BaseInfoVO {

    private Long id;
    @NotBlank(message = "类型不能不能为空")
    private String kind;
    @NotBlank(message = "json体不能为空")
    private String jsonBody;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getJsonBody() {
        return jsonBody;
    }

    public void setJsonBody(String jsonBody) {
        this.jsonBody = jsonBody;
    }
}
