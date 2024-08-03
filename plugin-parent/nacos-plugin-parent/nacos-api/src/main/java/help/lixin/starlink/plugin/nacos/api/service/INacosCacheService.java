package help.lixin.starlink.plugin.nacos.api.service;

import help.lixin.starlink.plugin.nacos.api.dto.instance.InstanceDetailResponse;

import java.util.List;

public interface INacosCacheService {

    // 从缓存中获取数据
    public InstanceDetailResponse getFromCache(String key);

    // 将数据放入缓存
    public void putInCache(String key, InstanceDetailResponse value);

    // 从缓存中删除数据
    public void removeFromCache(String key);

    // 清空缓存
    public void clearCache();

    //获取缓存数据列表
    public List<InstanceDetailResponse> cacheList();
}
