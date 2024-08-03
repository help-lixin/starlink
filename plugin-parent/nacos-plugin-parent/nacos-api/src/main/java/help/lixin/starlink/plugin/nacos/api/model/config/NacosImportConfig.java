package help.lixin.starlink.plugin.nacos.api.model.config;

import help.lixin.starlink.plugin.nacos.api.model.enums.NacosImportEnum;
import org.springframework.web.multipart.MultipartFile;


/**
 * @Author: 伍岳林
 * @Date: 2023/7/17 6:37 下午
 * @Description
 */
public class NacosImportConfig {

    String srcUser;
    String nameSpaceId;
    NacosImportEnum policy;
    MultipartFile file;

    public String getSrcUser() {
        return srcUser;
    }

    public void setSrcUser(String srcUser) {
        this.srcUser = srcUser;
    }

    public String getNameSpaceId() {
        return nameSpaceId;
    }

    public void setNameSpaceId(String nameSpaceId) {
        this.nameSpaceId = nameSpaceId;
    }

    public NacosImportEnum getPolicy() {
        return policy;
    }

    public void setPolicy(NacosImportEnum policy) {
        this.policy = policy;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
