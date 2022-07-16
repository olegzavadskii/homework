package com.tms.service;

import java.util.Scanner;

public class DriverService {

    private int rate;
    private PrintService printService;

    public DriverService(PrintService printService) {
        this.printService = printService;
    }

    public PrintService getPrintService() {
        return printService;
    }

    public void setRate() {
        Scanner scanner = new Scanner((System.in));
        System.out.println("Какая пара победит? Введите число от 1 до 3: ");
        this.rate = scanner.nextInt();
        if (this.rate < 1) {
            System.out.println("Вы ввели некорректное число. ");
            setRate();
        }
        if (this.rate > 3) {
            System.out.println("Вы ввели некорректное число. ");
            setRate();
        }
    }

    public void checkResult() {
        if (PrintService.getCoupleList().get(0).getNumber() == this.rate) {
            System.out.println("Вы выиграли!");
        } else {
            System.out.println("Вы проиграли!");
        }
    }

}
