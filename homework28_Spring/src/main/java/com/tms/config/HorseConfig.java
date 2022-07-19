package com.tms.config;

import com.tms.entity.Horse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class HorseConfig {

    @Bean(name = "horse")
    @Scope("prototype")
    public Horse getHorse() {
        return new Horse();
    }
}
