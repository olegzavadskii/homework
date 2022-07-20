package com.tms.entity;


import lombok.Data;

@Data
public class Horse {
    private int speed;

    public Horse() {
        this.speed = (int) (1 + (Math.random() * 5));
    }
}
