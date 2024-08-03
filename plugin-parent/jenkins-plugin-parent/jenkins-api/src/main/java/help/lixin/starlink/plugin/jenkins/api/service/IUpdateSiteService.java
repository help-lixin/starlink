package help.lixin.starlink.plugin.jenkins.api.service;

public interface IUpdateSiteService {

    // 检查站点
    boolean checkUpdateSite(String url);

    // 更新站点
    void updateSite(String url);
}
