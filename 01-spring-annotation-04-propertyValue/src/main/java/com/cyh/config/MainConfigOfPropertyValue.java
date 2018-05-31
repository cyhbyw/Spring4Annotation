package com.cyh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.cyh.bean.Person;

/**
 * 使用 @PropertySource 读取外部配置文件中的k/v保存到运行的环境变量中;
 * 加载完外部的配置文件以后使用${}取出配置文件的值
 */
@Configuration
@PropertySource(value = {"classpath:/person.properties"})
public class MainConfigOfPropertyValue {

    @Bean
    public Person person() {
        return new Person();
    }

}
