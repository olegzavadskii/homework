package com.tms.config;

import com.tms.entity.Rider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RiderConfig {
    @Bean
    @Scope("prototype")
    public Rider getRider() {
        return new Rider();
    }
}
