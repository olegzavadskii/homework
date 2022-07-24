package com.tms.config;

import com.tms.entity.Couple;
import com.tms.entity.Horse;
import com.tms.entity.Rider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EntityConfig {
    @Bean(name = "horse1")
    public Horse getHorse1() {
        return new Horse();
    }

    @Bean(name = "horse2")
    public Horse getHorse2() {
        return new Horse();
    }

    @Bean(name = "horse3")
    public Horse getHorse3() {
        return new Horse();
    }

    @Bean(name = "rider1")
    public Rider getRider1() {
        return new Rider();
    }

    @Bean(name = "rider2")
    public Rider getRider2() {
        return new Rider();
    }

    @Bean(name = "rider3")
    public Rider getRider3() {
        return new Rider();
    }

    @Bean(name = "couple1")
    public Couple getCouple1() {
        return new Couple(1, getHorse1(), getRider1());
    }

    @Bean(name = "couple2")
    public Couple getCouple2() {
        return new Couple(2, getHorse2(), getRider2());
    }

    @Bean(name = "couple3")
    public Couple getCouple3() {
        return new Couple(3, getHorse3(), getRider3());
    }
}
