package com.cyh;

import com.cyh.bean.Person;
import com.cyh.config.MainConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ForDebugTest {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        Person person = context.getBean(Person.class);
        System.out.println(person);
    }

}
