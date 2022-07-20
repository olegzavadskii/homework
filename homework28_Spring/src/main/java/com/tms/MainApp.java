package com.tms;

import com.tms.service.DriverService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class MainApp {
    public static void main(String[] args) {
//        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        AbstractApplicationContext annotContext = new AnnotationConfigApplicationContext("com.tms");
        DriverService driverService = annotContext.getBean(DriverService.class);
        try {
            driverService.getPrintService().toRace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        driverService.checkResult();
        annotContext.registerShutdownHook();
    }
}