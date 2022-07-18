package com.tms;

import com.tms.service.DriverService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        DriverService driverService = context.getBean(DriverService.class);
        driverService.getPrintService().toRace();
        driverService.checkResult();
    }
}