package help.lixin.starlink.plugin.jsch.service;

import java.util.Set;

/**
 * 查询
 */
public interface IQuerySshHostService {

    // 根据标签,查询出所有的:instanceCode
    Set<String> queryByLabel(Set<String> label);
}
