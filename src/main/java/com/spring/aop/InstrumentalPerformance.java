package com.spring.aop;

import com.spring.aspects.Audience;
import org.springframework.stereotype.Component;

@Component
public class InstrumentalPerformance implements Performance {

    @Audience
    @Override public void perform() {
        System.out.println("instrumental performance");
    }
}
