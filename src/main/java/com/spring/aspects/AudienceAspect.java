package com.spring.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AudienceAspect {

    @Around("@annotation(Audience)")
    public void enjoyPerformance(ProceedingJoinPoint joinPoint) {

        System.out.println("Silencing cell phones");
        System.out.println("Taking seats");
        try {
            joinPoint.proceed();
            System.out.println("Applauding the performance");
        } catch (Throwable throwable) {
            System.out.println("Bad performance, demanding refund");
        }
    }

}
