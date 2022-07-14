package com.tms.service;

import com.tms.entity.Couple;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class PrintService {
    private static List<Couple> coupleList;

    public PrintService() {
    }

    public void setCoupleList(List<Couple> coupleList) {
        this.coupleList = coupleList;
    }

    public static List<Couple> getCoupleList() {
        return coupleList;
    }

    public void toRace() {
        for (int i = 0; i < 5; i++) {
            if (i > 1) {
                Iterator<Couple> iterator = coupleList.iterator();
                while (iterator.hasNext()) {
                    Couple next = iterator.next();
                    next.setSumSpeed(next.getSumSpeed() * (1 + (int) (Math.random() * 5)));
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