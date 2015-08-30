package com.spring.qualifiers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier(value = "cake")
@Primary
public class Cake implements Dessert {

    @Override public void enjoyDessert() {
        System.out.println("enjoying cake");
    }
}
