package com.tms.config;

import com.tms.entity.Couple;
import com.tms.entity.Horse;
import com.tms.entity.Rider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoupleConfig {

    @Bean(name = "couple1")
    public Couple getCouple1(Horse horse, Rider rider) {
        return new Couple(1, horse, rider);
    }

    @Bean(name = "couple2")
    public Couple getCouple2(Horse horse, Rider rider) {
        return new Couple(2, horse, rider);
    }

    @Bean(name = "couple3")
    public Couple getCouple3(Horse horse, Rider rider) {
        return new Couple(3, horse, rider);
    }
}
