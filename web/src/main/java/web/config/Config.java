package web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@SuppressWarnings("unused")
@Configuration
@EnableCaching
@EnableScheduling
public class Config {
    public static final String EXCHANGES_CACHE_KEY = "exchanges";

    private final Logger logger = LoggerFactory.getLogger(Config.class);

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(EXCHANGES_CACHE_KEY);
    }

    @CacheEvict(allEntries = true, value = {EXCHANGES_CACHE_KEY})
    @Scheduled(fixedDelay = 5 * 60 * 1000, initialDelay = 500)
    public void reportCacheEvict() {
        logger.info(String.format("Flush Cache %s\n", new Date()));
    }
}