package com.atguigu.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class Cat implements InitializingBean, DisposableBean {

    public Cat() {
        System.out.println("Cat constructor...");
    }

    /**
     * Invoked by a BeanFactory on destruction of a singleton.
     */
    @Override
    public void destroy() {
        System.out.println("cat...destroy...");
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties
     */
    @Override
    public void afterPropertiesSet() {
        System.out.println("cat...afterPropertiesSet...");
    }

}
