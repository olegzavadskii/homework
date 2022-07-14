package com.tms.entity;


import lombok.Data;

@Data
public class Horse {
    private int speed;

    public Horse() {
        this.speed = 1 + (int) (Math.random() * 3);
    }
}
