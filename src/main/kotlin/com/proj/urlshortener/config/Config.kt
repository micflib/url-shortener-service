package com.proj.urlshortener.config

import com.proj.urlshortener.dto.CACHE_NAME
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.concurrent.ConcurrentMapCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableCaching
class Config {
    @Bean
    fun cacheManager(): CacheManager = ConcurrentMapCacheManager(CACHE_NAME)
}
