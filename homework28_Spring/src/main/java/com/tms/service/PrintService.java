package com.tms.service;

import com.tms.entity.Couple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PrintService {
    @Autowired
    private List<Couple> coupleList = new ArrayList<>();

    public PrintService() {
    }

    public void setCoupleList(List<Couple> coupleList) {
        this.coupleList = coupleList;
    }

    public List<Couple> getCoupleList() {
        return coupleList;
    }

    public void toRace() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            if (i > 0) {
                Iterator<Couple> iterator = coupleList.iterator();
                while (iterator.hasNext()) {
                    Couple next = iterator.next();
                    next.setSumSpeed(next.getSumSpeed() * ((int) (1 + (Math.random() * 5))));
                }
            }
//            Thread.sleep(1000);
            System.out.println("Результат после " + (i + 1) + " круга: ");
            List<Couple> sortedList = coupleList.stream()
                    .sorted(Couple::compareTo)
                    .collect(Collectors.toList());
            sortedList.forEach(System.out::println);
            setCoupleList(sortedList);
        }
    }
}