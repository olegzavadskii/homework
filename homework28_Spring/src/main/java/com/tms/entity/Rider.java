package com.tms.entity;

import lombok.Data;

@Data
public class Rider {
    private double level;

    public Rider() {
        this.level = (Math.random() + 1);
    }
}
