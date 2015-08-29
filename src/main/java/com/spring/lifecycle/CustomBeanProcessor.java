package com.spring.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomBeanProcessor implements BeanPostProcessor {

    @Override public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization() from BeanPostProcessor interface invoked for bean - " + beanName);
        return bean;
    }

    @Override public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization() from BeanPostProcessor interface invoked for bean - " + beanName);
        return bean;
    }
}
