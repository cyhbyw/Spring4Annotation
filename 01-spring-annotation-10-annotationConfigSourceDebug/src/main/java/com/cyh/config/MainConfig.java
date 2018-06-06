package com.cyh.config;

import com.cyh.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainConfig {

    @Bean
    public Person person() {
        return new Person();
    }

}
