package com.spring.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingletonBean implements Singleton{

    /*
     * When a prototype bean is injected inside a singleton, spring container gets only a single opportunity to initialize / wire in
     * this prototype dependency. Hence even though the bean is defined to be a prototype scoped bean, only a single instance is ever used.
     * See com.spring.wiring.AutowiredDependenciesDemo.testPrototypeBeanInitialization
     * To be able to use a Prototype scoped bean as a dependency within a Singleton scoped bean, there are two ways -
     * Method lookup injection
     * AOP scoped proxies
     * Remember, in case of look up method injection, proxy is created for singleton bean. But in case of scoped proxies, proxy is created
     * for prototype bean and wired into the singleton bean during the process of registering the singleton bean in the context.
     */
    @Autowired private Prototype prototype;

    public Prototype getPrototype() {
        return prototype;
    }

    public void greet() {
        System.out.println(prototype.getWelcomeMessage());
    }
}
