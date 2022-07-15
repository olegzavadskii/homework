package com.tms.entity;

import lombok.Data;

@Data
public class Rider {
    private double level;

    public Rider() {
        this.level = 1 + (Math.random() * 2);
    }
}
