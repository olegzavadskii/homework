package com.tms.service;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Scanner;

@Component
public class DriverService {

    private int rate;
    private PrintService printService;

    public DriverService(PrintService printService) {
        this.printService = printService;
    }

    public PrintService getPrintService() {
        return printService;
    }

    @PostConstruct
    public void setRate() {
        Scanner scanner = new Scanner((System.in));
        System.out.println("Какая пара победит? Введите число от 1 до 3: ");
        this.rate = scanner.nextInt();
        if (this.rate < 1 || this.rate > 3) {
            System.out.println("Вы ввели некорректное число. ");
            setRate();
        }
    }

    @PreDestroy
    public void checkResult() {
        if (printService.getCoupleList().get(0).getNumber() == this.rate) {
            System.out.println("Вы выиграли!");
        } else {
            System.out.println("Вы проиграли!");
        }
    }

}
