package com.cyh.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Dog implements ApplicationContextAware {

    public Dog() {
        System.out.println("Dog constructor...");
    }

    /**
     * 对象创建并赋值之后调用
     *
     * 实际上是 InitDestroyAnnotationBeanPostProcessor 这个 BeanPostProcessor 在工作
     * 本质是在 postProcessBeforeInitialization() 方法中被调用的
     */
    @PostConstruct
    public void init() {
        System.out.println("Dog....@PostConstruct...");
    }

    /**
     * 容器移除对象之前
     *
     * 实际上是 InitDestroyAnnotationBeanPostProcessor 这个 BeanPostProcessor 在工作
     * 本质是在 postProcessBeforeDestruction() 方法中被调用的
     */
    @PreDestroy
    public void destroy() {
        System.out.println("Dog....@PreDestroy...");
    }

    /**
     * 此方法的目的在于Debug，加深理解 BeanPostProcessor
     *
     * 通过Debug可以发现，此 setApplicationContext() 方法其实是在 ApplicationContextAwareProcessor 这个 BeanPostProcessor 的
     * postProcessBeforeInitialization() 方法中被调用的
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("ApplicationContextAware回调setApplicationContext：" + applicationContext);
    }
}

