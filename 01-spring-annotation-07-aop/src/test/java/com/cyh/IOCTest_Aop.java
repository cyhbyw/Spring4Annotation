package com.cyh;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cyh.aop.MathCalculator;
import com.cyh.config.MainConfigOfAop;

public class IOCTest_Aop {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfigOfAop.class);
        MathCalculator mathCalculator = applicationContext.getBean(MathCalculator.class);
        int result = mathCalculator.div(4, 2);
        System.out.println(result);

    }
}
