package com.tms.service;

import com.tms.entity.Couple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PrintService {
    private List<Couple> coupleList = new ArrayList<>();

    public PrintService() {
    }

    public void setCoupleList(List<Couple> coupleList) {
        this.coupleList = coupleList;
    }

    public List<Couple> getCoupleList() {
        return coupleList;
    }

    public void toRace() {
        for (int i = 0; i < 5; i++) {
            if (i > 0) {
                Iterator<Couple> iterator = coupleList.iterator();
                while (iterator.hasNext()) {
                    Couple next = iterator.next();
                    next.setSumSpeed(next.getSumSpeed() * ((Math.random() + 1)));
                }
            }
            System.out.println("Результат после " + (i + 1) + " круга: ");
            List<Couple> sortedList = coupleList.stream()
                    .sorted(Couple::compareTo)
                    .collect(Collectors.toList());
            sortedList.forEach(System.out::println);
            setCoupleList(sortedList);
        }
    }


}