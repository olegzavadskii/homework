package com.tms.config;

import com.tms.entity.Couple;
import com.tms.entity.Horse;
import com.tms.entity.Rider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public Horse getHorse1() {
        return new Horse();
    }

    @Bean
    public Horse getHorse2() {
        return new Horse();
    }

    @Bean
    public Horse getHorse3() {
        return new Horse();
    }

    @Bean
    public Rider getRider1() {
        return new Rider();
    }

    @Bean
    public Rider getRider2() {
        return new Rider();
    }

    @Bean
    public Rider getRider3() {
        return new Rider();
    }

    @Bean
    public Couple getCouple1() {
        return new Couple(1, getHorse1(), getRider1());
    }

    @Bean
    public Couple getCouple2() {
        return new Couple(2, getHorse2(), getRider2());
    }

    @Bean
    public Couple getCouple3() {
        return new Couple(3, getHorse3(), getRider3());
    }
}
