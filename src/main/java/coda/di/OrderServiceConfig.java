package coda.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import coda.orderService.OrderService;
import coda.orderService.IOrderService;

@Configuration
public class OrderServiceConfig {
    @Bean
    public IOrderService evaluationService() {
        return new OrderService(); 
    }
}

