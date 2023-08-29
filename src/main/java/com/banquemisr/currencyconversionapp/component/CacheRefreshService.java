package com.banquemisr.currencyconversionapp.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class CacheRefreshService {

    private final CacheManager cacheManager;

    public CacheRefreshService(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Scheduled(fixedRate = 1000 * 60 * 15) // Scheduled for every 15 minutes
    public void refreshCache() {
        cacheManager.getCacheNames().stream()
                .map(cacheManager::getCache)
                .filter(Objects::nonNull)
                .forEach(Cache::clear);
        log.info("Refreshing Redis Cache");
    }
}
