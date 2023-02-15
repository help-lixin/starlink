package help.lixin.eureka.api;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import help.lixin.eureka.api.status.EurekaInstanceStatus;


public interface EurekaClient {

    /**
     * 注册一个应用实例
     *
     * @param instanceInfo 应用信息
     */
    void registerInstance(InstanceInfo instanceInfo);


    /**
     * 删除一个实例
     *
     * @param appName    应用名
     * @param instanceId 实例id
     */
    void deleteInstance(String appName, String instanceId);

    /**
     * 发送一个应用实例心跳
     *
     * @param appName    应用名
     * @param instanceId 实例id
     */
    void heartbeat(String appName, String instanceId);

    /**
     * 列出所有应用
     *
     * @return json/xml
     */
    Applications applications();

    /**
     * 列出应用下的所有实例
     *
     * @param appName 应用名
     * @return json/xml
     */
    Application application(String appName);

    /**
     * 查询指定的实例
     *
     * @param appName    应用名
     * @param instanceId 实例id
     * @return json/xml
     */
    InstanceInfo instance(String appName, String instanceId);

    /**
     * 查询特定的实例
     *
     * @param instanceId 实例id
     * @return json/xml
     */
    InstanceInfo instance(String instanceId);

    /**
     * 中止/失效一个实例
     *
     * @param appName    应用名
     * @param instanceId 实例id
     */
    void outOfService(String appName, String instanceId);

    /**
     * 恢复一个实例到指定状态
     *
     * @param appName    应用名
     * @param instanceId 实例id
     * @param status     json/xml
     */
    void backInService(String appName, String instanceId, EurekaInstanceStatus status);

    /**
     * 更新实例的元数据
     *
     * @param appName    应用名
     * @param instanceId 实例id
     * @param key        键
     * @param value      值
     */
    void updateMetadata(String appName, String instanceId, String key, String value);

    /**
     * 在一个特定的vip地址查询所有实例
     *
     * @param vipAddress vip地址
     * @return json/xml
     */
    Applications vips(String vipAddress);

    /**
     * 在一个特定的安全vip地址查询所有实例
     *
     * @param svipAddress 安全的vip地址
     * @return json/xml
     */
    Applications svips(String svipAddress);
}
