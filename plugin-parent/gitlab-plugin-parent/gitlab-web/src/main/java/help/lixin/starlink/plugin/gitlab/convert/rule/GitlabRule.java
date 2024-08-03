package help.lixin.starlink.plugin.gitlab.convert.rule;

import help.lixin.starlink.plugin.gitlab.dto.group.AccessLevel;

/**
 * @Author: 伍岳林
 * @Date: 2023/11/24 6:44 下午
 * @Description
 */
public class GitlabRule {

    public Integer accessLevelToInteger(AccessLevel accessLevel){
        if (accessLevel == null){
            return null;
        }
        return accessLevel.value;
    }
}
