package com.tms.entity;


import lombok.Data;

@Data
public class Horse {
    private double speed;

    public Horse() {
        this.speed = (Math.random() + 1);
    }
}
