package help.lixin.plugin.eureka.api.service;

import java.util.List;

import com.netflix.appinfo.InstanceInfo;

public interface IEurekaCacheManager {

    // 配置方法，用于设置缓存的过期时间
    public void configureCache(Integer expireAfterWriteMinutes);

    // 从缓存中获取数据
    public InstanceInfo getFromCache(String key);

    // 将数据放入缓存
    public Boolean putInCache(String key, InstanceInfo value);

    // 从缓存中删除数据
    public Boolean removeFromCache(String key);

    // 清空缓存
    public Boolean clearCache();

    // 获取缓存列表
    public List<InstanceInfo> cacheList();
}
