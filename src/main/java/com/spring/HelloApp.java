package com.spring;

import com.spring.lifecycle.HelloService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
        HelloService helloService = context.getBean(HelloService.class);
        System.out.println("BeanNameAware set the bean name to " + helloService.getBeanName());
        System.out.println("Using BeanFactory set by BeanFactoryAware, HelloService bean is singleton ?  " + helloService.getBeanFactory().isSingleton("helloService"));
        System.out.println(helloService.sayHello());
        context.close();
    }
}
