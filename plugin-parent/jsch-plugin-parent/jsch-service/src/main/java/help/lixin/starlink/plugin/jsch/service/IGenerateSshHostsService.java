package help.lixin.starlink.plugin.jsch.service;


import java.util.List;

public interface IGenerateSshHostsService {
    String generateHosts(String labelKey, List<String> instanceCodes);
}
