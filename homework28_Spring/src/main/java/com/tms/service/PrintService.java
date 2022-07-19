package com.tms.service;

import com.tms.config.MyConfig;
import com.tms.entity.Couple;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PrintService {
    private List<Couple> coupleList = new ArrayList<>();

    {
        MyConfig config = new MyConfig();
        Couple couple1 = config.getCouple1();
        Couple couple2 = config.getCouple2();
        Couple couple3 = config.getCouple3();
        coupleList.add(couple1);
        coupleList.add(couple2);
        coupleList.add(couple3);
    }

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