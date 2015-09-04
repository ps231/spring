package com.spring.autowiring.modes;

import org.springframework.stereotype.Component;

@Component(value = "organization")
public class Company implements Party {
    @Override public void organizeParty() {
        System.out.println("Company organizing party.");
    }
}
