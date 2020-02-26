package com.jack.store.config;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheEventListenerConfigurationBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.ehcache.event.EventType;
import org.ehcache.jsr107.Eh107Configuration;
import org.hibernate.cache.jcache.ConfigSettings;
import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
/*This annotation registers CacheInterceptor or AnnotationCacheAspect,
 which will detect cache annotations like @Cacheable, @CachePut, and @CacheEvict.*/
@EnableCaching
public class EHCacheConfig {

    @Slf4j
    @NoArgsConstructor
    private static class CacheEventLogger implements CacheEventListener<Object, Object> {

        @Override
        public void onEvent(CacheEvent<?, ?> cacheEvent) {
            log.debug("Cache Key: {}, Cache Old Value: {}, Cache New Value: {}", cacheEvent.getKey(), cacheEvent.getOldValue(), cacheEvent.getNewValue());
        }
    }

    @Bean
    public javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration() {
        long cacheSize = 100;
        long ttl = 60;

        org.ehcache.config.CacheConfiguration<Object, Object> cacheConfiguration = CacheConfigurationBuilder
                .newCacheConfigurationBuilder(Object.class, Object.class, ResourcePoolsBuilder
                        .heap(cacheSize))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ttl)))
                .add(
                        CacheEventListenerConfigurationBuilder
                                .newEventListenerConfiguration(new EHCacheConfig.CacheEventLogger(), EventType.CREATED, EventType.UPDATED, EventType.EXPIRED)
                                .unordered().asynchronous()
                )
                .build();

        return Eh107Configuration.fromEhcacheCacheConfiguration(cacheConfiguration);
    }

    @Bean
    public HibernatePropertiesCustomizer hibernatePropertiesCustomizer(javax.cache.CacheManager cacheManager) {
        return hibernateProperties -> {
            hibernateProperties.put(ConfigSettings.CACHE_MANAGER, cacheManager);
            hibernateProperties.put(ConfigSettings.MISSING_CACHE_STRATEGY, "create");
        };
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