package com.cyh.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Cat implements InitializingBean, DisposableBean {

    public Cat() {
        System.out.println("è constructor...");
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties
     */
    @Override
    public void afterPropertiesSet() {
        System.out.println("è...afterPropertiesSet...");
    }

    /**
     * Invoked by a BeanFactory on destruction of a singleton.
     */
    @Override
    public void destroy() {
        System.out.println("è...destroy...");
    }

}

