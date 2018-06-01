package com.cyh.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        StringBuilder content = new StringBuilder();
        content.append("MyBeanFactoryPostProcessor...postProcessBeanFactory()...callback...").append("\n");
        int count = beanFactory.getBeanDefinitionCount();
        content.append("当前BeanFactory中有 " + count + " 个Bean，它们分别是：").append("\n");
        String[] names = beanFactory.getBeanDefinitionNames();
        for (String name : names) {
            content.append("  ").append(name).append("\n");
        }
        System.out.println(content.toString());
    }

}
