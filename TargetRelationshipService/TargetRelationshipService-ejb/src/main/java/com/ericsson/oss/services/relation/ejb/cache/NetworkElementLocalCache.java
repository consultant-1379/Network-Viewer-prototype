package com.ericsson.oss.services.relation.ejb.cache;

import javax.cache.Cache;

import com.ericsson.oss.itpf.modeling.annotation.cache.CacheMode;
import com.ericsson.oss.itpf.modeling.annotation.cache.EvictionStrategy;
import com.ericsson.oss.itpf.sdk.cache.classic.CacheConfiguration;
import com.ericsson.oss.itpf.sdk.cache.classic.CacheProviderBean;

/**
 * It stores all NetworkElements PoIds and its targetType.
 */
public class NetworkElementLocalCache {

    private static final String CACHE_NAME = "NetworkElementLocalCache";
    private Cache<Long, String> cache;

    public NetworkElementLocalCache() {
        final CacheConfiguration cacheConfiguration = new CacheConfiguration.Builder().timeToLive(1000).evictionStrategy(EvictionStrategy.LRU)
                   .cacheMode(CacheMode.LOCAL).build();

        final CacheProviderBean bean = new CacheProviderBean();
        cache = bean.createOrGetCache(CACHE_NAME, cacheConfiguration);
    }
}
