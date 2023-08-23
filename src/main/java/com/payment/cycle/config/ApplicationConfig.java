package com.payment.cycle.config;

import com.payment.cycle.infrastructure.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public PaymentService payments() {
        PaymentService paymentService = new PaymentService();
        System.out.println("Inicializando payment Bean Service");
        return paymentService;
    }
}
