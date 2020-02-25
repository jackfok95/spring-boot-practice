package com.jack.store.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching /*This annotation registers CacheInterceptor or AnnotationCacheAspect,
 which will detect cache annotations like @Cacheable, @CachePut, and @CacheEvict.*/
public class EHCacheConfig {


}