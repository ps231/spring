package com.spring.autowiring.modes;

import org.springframework.stereotype.Component;

@Component
public class Person implements Party {
    @Override public void organizeParty() {
        System.out.println("Person organizing party.");
    }
}
