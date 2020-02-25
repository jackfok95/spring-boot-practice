package com.jack.store.config;

import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.jsr107.Eh107Configuration;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
/*This annotation registers CacheInterceptor or AnnotationCacheAspect,
 which will detect cache annotations like @Cacheable, @CachePut, and @CacheEvict.*/
@EnableCaching
public class EHCacheConfig {

    @Bean
    public javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration() {
        long cacheSize = 100;
        long ttl = 60;

        org.ehcache.config.CacheConfiguration<Object, Object> cacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder
                        .heap(cacheSize))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ttl)))
                .build();

        return Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfiguration);
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer(javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration ) {
        return cm -> {
            cm.createCache(com.jack.store.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.jack.store.domain.User.class.getName() + ".products", jcacheConfiguration);
            cm.createCache(com.jack.store.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache("userCache", jcacheConfiguration);
        };
    }

}