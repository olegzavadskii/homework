package com.tms.entity;

import lombok.Data;

@Data
public class Rider {
    private int level;

    public Rider() {
        this.level = (int) (1 + (Math.random() * 5));
    }
}
