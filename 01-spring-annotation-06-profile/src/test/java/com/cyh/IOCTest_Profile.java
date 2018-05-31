package com.cyh;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cyh.bean.Yellow;
import com.cyh.config.MainConfigOfProfile;

public class IOCTest_Profile {

    /**
     * 如何切环境：
     * 1、使用命令行动态参数: 在虚拟机参数位置加载 -Dspring.profiles.active=test
     * 2、代码的方式激活某种环境
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfigOfProfile.class);
        String[] dataSources = applicationContext.getBeanNamesForType(DataSource.class);
        for (String x : dataSources) {
            System.out.println(x);
        }
    }

    /**
     * 2、代码的方式激活某种环境
     */
    @Test
    public void test01() {
        //1、创建一个applicationContext（注意：是无参数的构造器）
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        //2、设置需要激活的环境
        applicationContext.getEnvironment().setActiveProfiles("test");
        //3、注册主配置类
        applicationContext.register(MainConfigOfProfile.class);
        //4、启动刷新容器
        applicationContext.refresh();

        String[] namesForType = applicationContext.getBeanNamesForType(DataSource.class);
        for (String string : namesForType) {
            System.out.println(string);
        }

        Yellow y = applicationContext.getBean(Yellow.class);
        System.out.println(y);

        applicationContext.close();
    }
}
