package com.tms.entity;

import lombok.Data;

@Data
public class Rider {
    private int level;

    public Rider() {
        this.level = 1 + (int) (Math.random() * 3);
    }
}
