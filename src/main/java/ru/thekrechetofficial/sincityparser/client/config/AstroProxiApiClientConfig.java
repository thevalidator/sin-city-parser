/*
 * The Krechet Software
 */

package ru.thekrechetofficial.sincityparser.client.config;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * @author theValidator <the.validator@yandex.ru>
 */
public class AstroProxiApiClientConfig {
    
    @Value("${proxy.api.token}")
    private String token;
    
    @Bean
    public RequestInterceptor requestInterceptor() {
        
        return requestTemplate -> {
            requestTemplate.header("Accept", "application/json");
            requestTemplate.header("Content-Type", "application/json");
            requestTemplate.query("token", token);
        };
        
    }

}
