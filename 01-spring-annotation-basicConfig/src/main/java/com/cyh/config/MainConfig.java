package com.cyh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.cyh.bean.Person;

/**
 * 排除：
 * excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = {Controller.class})}
 *
 * 只要：注意需要设置 useDefaultFilters = false 否则不生效
 * includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Controller.class)}, useDefaultFilters = false
 *
 * 只要某个指定类型：
 * @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = BookService.class)
 */
@Configuration
@ComponentScans(value = {@ComponentScan(value = "com.cyh",
        includeFilters = {@ComponentScan.Filter(type = FilterType.CUSTOM, classes = MyTypeFilter.class)},
        useDefaultFilters = false)})
public class MainConfig {

    /**
     * 给容器中注册一个Bean，类型为返回值的类型，
     * 默认是用方法名作为id，但是如果在 @Bean 注解上使用 value/name 属性指定，则以指定的名称为准
     * @return
     */
    @Bean("person")
    public Person person01() {
        return new Person("lisi", 20);
    }


}
