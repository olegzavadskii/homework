package com.tms.entity;

import lombok.Data;

@Data
public class Couple implements Comparable<Couple> {
    private int number;
    private Horse horse;
    private Rider rider;
    private int sumSpeed;

    public Couple(int number, Horse horse, Rider rider) {
        this.number = number;
        this.horse = horse;
        this.rider = rider;
        this.sumSpeed = horse.getSpeed() * rider.getLevel();
    }

    public int getSumSpeed() {
        return sumSpeed;
    }

    @Override
    public int compareTo(Couple o) {
        return o.getSumSpeed() - this.getSumSpeed();
    }
}
