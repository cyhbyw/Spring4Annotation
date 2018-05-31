package com.cyh;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.cyh.config.MainConfigOfAutowire;
import com.cyh.service.BookService;

public class IOCTest_Autowired {

    @Test
    public void test01() {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainConfigOfAutowire.class);
        System.out.println("================容器初始化完成=====================");

        BookService bookService = applicationContext.getBean(BookService.class);
        System.out.println(bookService);

        applicationContext.close();
    }
}
