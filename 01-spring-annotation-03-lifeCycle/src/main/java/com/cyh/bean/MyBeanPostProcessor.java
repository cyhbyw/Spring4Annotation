package com.cyh.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器：初始化前后进行处理工作
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     * before InitializingBean#afterPropertiesSet or a custom init-method
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessBeforeInitialization... " + beanName + " => " + bean);
        return bean;
    }

    /**
     * after InitializingBean#afterPropertiesSet or a custom init-method
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("postProcessAfterInitialization... " + beanName + " => " + bean);
        return bean;
    }

}

