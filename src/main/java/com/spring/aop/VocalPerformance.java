package com.spring.aop;

import org.springframework.stereotype.Component;

@Component
public class VocalPerformance implements Performance {
    @Override public void perform() {
        System.out.println("vocal performance");
    }
}
