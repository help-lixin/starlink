package help.lixin.starlink.plugin.jsch.api.service;

import help.lixin.starlink.plugin.jsch.api.dto.Download;
import help.lixin.starlink.plugin.jsch.api.dto.Upload;
import help.lixin.starlink.plugin.jsch.api.properties.JschProperties;

public interface ISecureCopyService {

    /**
     * 下载远程目录(文件)到本地目录
     *
     * @param download
     * @return
     */
    String download(Download download);

    /**
     * 把本地目录或者文件,通过SSH拷贝到远程主机上(scp指令)
     *
     * @param upload
     * @throws Exception
     */
    void upload(Upload upload) throws Exception;

    JschProperties getJschProperties();
}