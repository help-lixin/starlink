package help.lixin.mvn.compile.service;

import java.util.List;
import java.util.Map;

/**
 * 容器管理
 * 1. 创建容器
 * 2. 销毁容器
 * 3. 查看容器日志
 */
public interface IContainerService {

    String mvnCompile(
            // 容器唯一名称
            String containerUniqueName,
            // registry.cn-hangzhou.aliyuncs.com/acs/maven:latest
            String image,
            // /usr/src/mymaven
            String workingDir,
            // key: /tmp/583871138 value: /usr/src/mymaven
            Map<String, String> binds,
            // "mvn", "clean", "install"
            List<String> cmds) throws Exception;
}
