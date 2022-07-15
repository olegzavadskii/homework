package com.tms.entity;

import lombok.Data;

@Data
public class Couple implements Comparable<Couple> {
    private int number;
    private Horse horse;
    private Rider rider;
    private double sumSpeed;

    public Couple(int number, Horse horse, Rider rider) {
        this.number = number;
        this.horse = horse;
        this.rider = rider;
        this.sumSpeed = horse.getSpeed() * rider.getLevel();
    }

    public double getSumSpeed() {
        return sumSpeed;
    }

    @Override
    public int compareTo(Couple o) {
        return (int) (o.getSumSpeed() - this.getSumSpeed());
    }
}
