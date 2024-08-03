package help.lixin.starlink.plugin.nacos.api.service.impl;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import help.lixin.starlink.plugin.nacos.api.dto.instance.InstanceDetailResponse;
import help.lixin.starlink.plugin.nacos.api.service.INacosCacheService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/20 3:11 下午
 * @Description
 */
public class NacosCacheService implements INacosCacheService {

    private Cache<String, InstanceDetailResponse> cache;

    //扩容权重
    private final Integer WEIGHER = 1;
    //初始容量
    private final Integer INI_CAPACITY = 200;

    public NacosCacheService(Integer expireTime) {
        // 初始化缓存
        cache = Caffeine.newBuilder().expireAfterWrite(expireTime, TimeUnit.SECONDS) //
                .maximumWeight(INI_CAPACITY).weigher((k, v) -> WEIGHER) //
                .build();
    }

    // 从缓存中获取数据
    public InstanceDetailResponse getFromCache(String key) {
        return cache.getIfPresent(key);
    }

    // 将数据放入缓存
    public void putInCache(String key, InstanceDetailResponse value) {
        cache.put(key, value);
    }

    // 从缓存中删除数据
    public void removeFromCache(String key) {
        cache.invalidate(key);
    }

    // 清空缓存
    public void clearCache() {
        cache.invalidateAll();
    }

    public List<InstanceDetailResponse> cacheList(){
        List<InstanceDetailResponse> resultList = new ArrayList<>();
        cache.asMap().forEach((k,v)->{
            resultList.add(v);
        });
        return resultList;
    }

}
