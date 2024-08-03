package help.lixin.starlink.plugin.nacos.request.config;

import help.lixin.starlink.request.PageRequest;
import io.swagger.annotations.Api;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/17 10:25 上午
 * @Description
 */
@Api(tags = "历史版本配置 分页请求对象")
public class NacosPageListHistoryConfigRequest extends PageRequest {

    private String dataId;
    private String nacosGroup;
    private String namespaceId;

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getNacosGroup() {
        return nacosGroup;
    }

    public void setNacosGroup(String nacosGroup) {
        this.nacosGroup = nacosGroup;
    }

    public String getNamespaceId() {
        return namespaceId;
    }

    public void setNamespaceId(String namespaceId) {
        this.namespaceId = namespaceId;
    }
}
