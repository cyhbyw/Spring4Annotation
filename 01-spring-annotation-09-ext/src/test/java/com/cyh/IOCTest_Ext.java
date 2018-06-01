package com.cyh;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cyh.ext.ExtConfig;

public class IOCTest_Ext {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ExtConfig.class);
        System.out.println("容器创建完成============================================");
        applicationContext.publishEvent(new ApplicationEvent(new String("我发布的事件")) {});
        applicationContext.close();
    }

}
