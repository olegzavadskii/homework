package com.tms.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class Book {
    private UUID uuid;
    private String author;
    private String name;
    private double cost;

    public Book(String author, String name, double cost) {
        this.author = author;
        this.name = name;
        this.cost = cost;
        uuid = UUID.randomUUID();
    }
}
