package com.app.alpha.Clases.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class DlpConfiguration {

    @Bean(name="downloadExecutor")
    public Executor downloadExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(25);

        executor.setThreadNamePrefix("DlpDownload-");
        executor.initialize();
        return executor;
    }


}
