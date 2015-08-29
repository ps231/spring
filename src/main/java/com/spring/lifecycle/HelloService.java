package com.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class HelloService implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    private String beanName;
    private BeanFactory beanFactory;
    private ApplicationContext applicationContext;

    public String sayHello() {
        return "Hello world!";
    }

    public String getBeanName() {
        return beanName;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override public void setBeanName(final String beanName) {
        System.out.println("setBeanName() from BeanNameAware interface invoked");
        this.beanName = beanName;
    }

    @Override public void setBeanFactory(final BeanFactory beanFactory) throws BeansException {
        System.out.println("setBeanFactory() from BeanFactoryAware interface invoked");
        this.beanFactory = beanFactory;
    }

    @Override public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext() from ApplicationContextAware interface invoked");
        this.applicationContext = applicationContext;
    }

    @Override public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet() from InitializingBean interface invoked");
    }

    /*
     * We can use either @PostConstruct or Initializing Bean to have custom initializing of spring beans
     */
    @PostConstruct public void customInit() {
        System.out.println("Defining a custom init method");
    }

    @Override public void destroy() throws Exception {
        System.out.println("destroy() from DisposableBean interface invoked");
    }

    /*
     * We can use either @PreDestroy or Disposable Bean to have custom destruction of spring beans
     */
    @PreDestroy public void customDestroy() {
        System.out.println("Defining a custom destroy method");
    }
}
