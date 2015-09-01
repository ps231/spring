package com.spring.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/*
 * proxyMode creates a AOP scoped Proxy that is wired into the SingletonBean.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
public class PrototypeBean implements Prototype {

    private String welcomeMessage;

    public String getWelcomeMessage() {

        return welcomeMessage;
    }

    public void setWelcomeMessage(final String welcomeMessage) {

        this.welcomeMessage = welcomeMessage;
    }
}
