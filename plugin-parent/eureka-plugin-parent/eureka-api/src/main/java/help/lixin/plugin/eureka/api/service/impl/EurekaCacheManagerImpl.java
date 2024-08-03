package help.lixin.plugin.eureka.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.netflix.appinfo.InstanceInfo;

import help.lixin.plugin.eureka.api.service.IEurekaCacheManager;

/**
 * @Author: 伍岳林
 * @Date: 2023/7/20 3:11 下午
 * @Description
 */
public class EurekaCacheManagerImpl implements IEurekaCacheManager {

    private Cache<String, InstanceInfo> cache;

    // 当前容量
    private Integer currentMaxWeight;
    // 负载因子
    private final Double LOAD_FACTOR = 0.75;
    // 缩容因子
    private final Double REDUCTION_FACTOR = 0.25;
    // 扩容权重
    private final Integer WEIGHER = 1;
    // 扩容缩容倍数
    private final Integer CAPACITY_AMOUNT = 2;
    // 初始容量
    private final Integer INI_CAPACITY = 200;

    // 配置方法，用于设置缓存的过期时间
    public void configureCache(Integer expireTime) {
        // 初始化缓存
        cache = Caffeine.newBuilder().maximumWeight(INI_CAPACITY).expireAfterWrite(expireTime, TimeUnit.SECONDS)
            .weigher((k, v) -> WEIGHER).build();
        currentMaxWeight = INI_CAPACITY;
    }

    // 从缓存中获取数据
    public InstanceInfo getFromCache(String key) {
        return cache.getIfPresent(key);
    }

    // 将数据放入缓存
    public Boolean putInCache(String key, InstanceInfo value) {
        cache.put(key, value);

        // 判断当前缓存是否需要扩容
        ConcurrentMap<String, InstanceInfo> detailResponseConcurrentMap = cache.asMap();
        Integer currentWeight = detailResponseConcurrentMap.size();
        if (currentWeight >= currentMaxWeight * LOAD_FACTOR) {
            // 扩容一倍
            currentMaxWeight *= CAPACITY_AMOUNT;
            cache = Caffeine.newBuilder().maximumWeight(currentMaxWeight).weigher((k, v) -> WEIGHER).build();
            cache.putAll(detailResponseConcurrentMap);
        }

        return true;
    }

    // 从缓存中删除数据
    public Boolean removeFromCache(String key) {
        InstanceInfo ifPresent = cache.getIfPresent(key);
        if (ifPresent == null) {
            return true;
        }

        cache.invalidate(key);

        // 判断当前缓存是否需要缩容
        ConcurrentMap<String, InstanceInfo> curMap = cache.asMap();
        Integer currentWeight = curMap.size();
        if (currentWeight <= currentMaxWeight * REDUCTION_FACTOR) {
            // 缩容一半
            currentMaxWeight /= CAPACITY_AMOUNT;

            // 低于默认容量恢复到默认容量
            if (currentMaxWeight <= INI_CAPACITY) {
                currentMaxWeight = INI_CAPACITY;
            }

            cache = Caffeine.newBuilder().maximumWeight(currentMaxWeight).weigher((k, v) -> WEIGHER).build();
            cache.putAll(curMap);
        }

        return true;
    }

    // 清空缓存
    public Boolean clearCache() {
        cache.invalidateAll();
        return true;
    }

    // 获取缓存列表
    public List<InstanceInfo> cacheList() {
        List<InstanceInfo> resultList = new ArrayList<>();
        cache.asMap().forEach((k, v) -> {
            resultList.add(v);
        });
        return resultList;
    }

    public EurekaCacheManagerImpl(Integer expireTime) {
        configureCache(expireTime);
    }
}
