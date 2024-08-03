package help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.impl;

import help.lixin.starlink.plugin.jenkins.xml.freestyle.delegate.IBuildDependencyDelegate;
import help.lixin.starlink.plugin.jenkins.xml.pojo.trigger.AbstractTrigger;
import help.lixin.starlink.plugin.jenkins.xml.pojo.trigger.ReverseBuildTrigger;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BuildDependencyDelegate implements IBuildDependencyDelegate {

    @Override
    public List<AbstractTrigger> apply(String instanceCode, List<String> buildDependencys) {
        List<AbstractTrigger> result = new ArrayList<>();
        String upstreamProjects = buildDependencys //
                .stream() //
                .filter(item -> null != item) //
                .collect(Collectors.joining(","));
        ReverseBuildTrigger trigger = new ReverseBuildTrigger();
        trigger.setUpstreamProjects(upstreamProjects);
        result.add(trigger);
        return result;
    }
}
