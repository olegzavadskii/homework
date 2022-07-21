package com.tms.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
@Aspect
public class InfoAspect {

    @Pointcut("@annotation(com.tms.annotation.Loggable)")
    public void pointAspect() {
    }

    @Around("pointAspect()")
    public void infoAboutMethod(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long start = System.currentTimeMillis();
        System.out.println("Время вызова метода " + myFormat.format(start));
        proceedingJoinPoint.proceed();
        long finish = System.currentTimeMillis();
        System.out.println("\n" + "Длительность работы метода " + proceedingJoinPoint.getSignature().getName() + " составила " + (finish - start) + " милисекунд");
    }

}