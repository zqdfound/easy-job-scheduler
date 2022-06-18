package com.zqdfound.easyjobscheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Thread pool configuration
 * @author zhuangqingdian
 * @date 2022/6/17
 */
@Configuration
public class TaskSchedulerConfig {
    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler threadPool = new ThreadPoolTaskScheduler();
        threadPool.setPoolSize(100);
        threadPool.setRemoveOnCancelPolicy(true);
        threadPool.setThreadNamePrefix("job-scheduler-");
        return threadPool;
    }
}
