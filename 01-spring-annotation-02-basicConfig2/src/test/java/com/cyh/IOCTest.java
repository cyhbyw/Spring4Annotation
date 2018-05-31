package com.cyh;

import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.Environment;

import com.cyh.bean.Blue;
import com.cyh.bean.Person;
import com.cyh.config.MainConfig2;

public class IOCTest {
    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig2.class);

    @Test
    public void test02() {
        System.out.println("ioc容器创建完成....");

        System.out.println("第 1 次获取，开始");
        Object bean = applicationContext.getBean("person");
        System.out.println("第 1 次获取，结束");

        System.out.println("第 2 次获取，开始");
        Object bean2 = applicationContext.getBean("person");
        System.out.println("第 2 次获取，结束");

        System.out.println(bean == bean2);
    }

    @Test
    public void test03() {
        Environment environment = applicationContext.getEnvironment();
        String osName = environment.getProperty("os.name");
        System.out.println(osName);

        Map<String, Person> persons = applicationContext.getBeansOfType(Person.class);
        System.out.println(persons);
    }

    @Test
    public void testImport() {
        String[] definitionNames = applicationContext.getBeanDefinitionNames();
        for (String name : definitionNames) {
            System.out.println(name);
        }

        Blue blue = applicationContext.getBean(Blue.class);
        System.out.println(blue);

        // 工厂Bean获取的调用 getObject() 方法创建的对象
        Object colorFactoryBean = applicationContext.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean.getClass());
        Object colorFactoryBean2 = applicationContext.getBean("colorFactoryBean");
        System.out.println(colorFactoryBean == colorFactoryBean2);
        // 获取工厂Bean本身需要加一个&
        Object colorFactoryBean3 = applicationContext.getBean("&colorFactoryBean");
        System.out.println(colorFactoryBean3.getClass());
    }

}
