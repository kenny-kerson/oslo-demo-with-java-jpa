package com.kenny.demo.oslodemowithjavajpa.hub.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean(name = "supplyAsyncExecutor")
    public Executor supplyAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(1);
        threadPoolTaskExecutor.setMaxPoolSize(Integer.MAX_VALUE);
        threadPoolTaskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        threadPoolTaskExecutor.setThreadNamePrefix("supplyAsyncExecutor-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    @Bean(name = "thenAsyncExecutor")
    public Executor thenAsyncExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(1);
        threadPoolTaskExecutor.setMaxPoolSize(Integer.MAX_VALUE);
        threadPoolTaskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        threadPoolTaskExecutor.setThreadNamePrefix("thenAsyncExecutor-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
