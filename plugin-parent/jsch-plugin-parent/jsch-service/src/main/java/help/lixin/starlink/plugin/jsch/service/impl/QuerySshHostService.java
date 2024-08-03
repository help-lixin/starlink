package help.lixin.starlink.plugin.jsch.service.impl;

import help.lixin.starlink.plugin.jsch.mapper.SshHostsMapper;
import help.lixin.starlink.plugin.jsch.domain.SshHosts;
import help.lixin.starlink.plugin.jsch.service.IQuerySshHostService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class QuerySshHostService implements IQuerySshHostService {
    private SshHostsMapper sshHostsMapper;

    public QuerySshHostService(SshHostsMapper sshHostsMapper) {
        this.sshHostsMapper = sshHostsMapper;
    }

    @Override
    public Set<String> queryByLabel(Set<String> label) {
        Set<String> instances = new HashSet<>(0);
        List<SshHosts> sshHosts = sshHostsMapper.selectSshHostListByLabel(label);
        if (!sshHosts.isEmpty()) {
            Set<String> tmp = sshHosts.stream().map(SshHosts::getSshInstanceCode).distinct().collect(Collectors.toSet());
            instances.addAll(tmp);
        }
        return instances;
    }
}
