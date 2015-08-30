package com.spring.qualifiers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "iceCream")
public class IceCream implements Dessert {

    @Override public void enjoyDessert() {
        System.out.println("enjoying icecream");
    }
}
