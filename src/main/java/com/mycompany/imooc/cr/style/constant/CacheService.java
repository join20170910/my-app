package com.mycompany.imooc.cr.style.constant;

import com.google.common.cache.Cache;

public class CacheService {
    private Cache<String, User> cache;

    public void cache(User user) {
        cache.put("user_" + user.getId(), user);
    }

    public User getUserFromCache(String id) {
        return cache.getIfPresent("user" + id);
    }
}
