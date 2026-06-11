package com.example.demo;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private CacheManager cacheManager;

    private static final String USER_CACHE_NAME = "userInfoCache";

    // ========== 业务方法：搭配Spring Cache三大注解 ==========
    // 查询：@Cacheable 命中缓存不走DB
    @Cacheable(value = USER_CACHE_NAME, key = "#id")
    public User getUserById(Long id) {
        System.out.println("===== 未命中缓存，查询模拟HashMap数据库 =====");
        return userDao.getById(id);
    }

    // 更新：@CachePut 更新DB同时强制刷新缓存
    @CachePut(value = USER_CACHE_NAME, key = "#user.id")
    public User saveOrUpdateUser(User user) {
        System.out.println("===== 更新模拟HashMap数据库 =====");
        return userDao.update(user);
    }

    // 删除：@CacheEvict 删除DB同时清理缓存
    @CacheEvict(value = USER_CACHE_NAME, key = "#id")
    public void deleteUser(Long id) {
        System.out.println("===== 删除模拟HashMap数据库 =====");
        userDao.deleteById(id);
    }

    // ========== 工具方法：打印Caffeine缓存里所有key-value（演示查看缓存数据） ==========
    public void printAllCaffeineCache() {
        CaffeineCache caffeineCache = (CaffeineCache) cacheManager.getCache(USER_CACHE_NAME);
        if (caffeineCache == null) {
            System.out.println("当前缓存为空");
            return;
        }
        // 获取Caffeine原生缓存对象，拿到全量缓存快照
        Cache<Object, Object> nativeCache = caffeineCache.getNativeCache();
        Map<Object, Object> cacheData = nativeCache.asMap();
        System.out.println("\n【当前Caffeine缓存全部数据】：" + cacheData + "\n");
    }
}