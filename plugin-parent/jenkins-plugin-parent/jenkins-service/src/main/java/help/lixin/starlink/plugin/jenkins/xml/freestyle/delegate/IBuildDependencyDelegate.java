package help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate;

import help.lixin.starlink.plugin.jenkins.xml.pojo.trigger.AbstractTrigger;

import java.util.List;

public interface IBuildDependencyDelegate {
    List<AbstractTrigger> apply(String instanceCode, List<String> buildDependencys);
}
