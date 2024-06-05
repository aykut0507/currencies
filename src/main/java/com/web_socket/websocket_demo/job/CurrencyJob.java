package com.web_socket.websocket_demo.job;

import com.web_socket.websocket_demo.integration.IntegrationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
public class CurrencyJob {
    private final IntegrationService integrationService;

    public CurrencyJob(IntegrationService integrationService) {
        this.integrationService = integrationService;
    }

    @Scheduled(fixedDelay=10000)
    public void scheduleFixedDelayTask() throws Exception {
        integrationService.getCurrenciesWithJob();
    }
}
