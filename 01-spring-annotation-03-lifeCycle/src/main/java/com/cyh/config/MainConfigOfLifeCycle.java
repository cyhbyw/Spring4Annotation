package com.cyh.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.cyh.bean.Car;

/**
 * bean的生命周期：
 * 		bean创建---初始化----销毁的过程
 * 容器管理bean的生命周期；
 * 我们可以自定义初始化和销毁方法；容器在bean进行到当前生命周期的时候来调用我们自定义的初始化和销毁方法
 *
 * 构造（对象创建）
 * 		单实例：在容器启动的时候创建对象
 * 		多实例：在每次获取的时候创建对象
 *
 *
 * BeanPostProcessor.postProcessBeforeInitialization
 * 初始化：
 * 		对象创建完成，并赋值好，调用初始化方法
 * BeanPostProcessor.postProcessAfterInitialization
 * 销毁：
 * 		单实例：容器关闭的时候
 * 		多实例：容器不会管理这个bean；容器不会调用销毁方法
 *
 *
 * 遍历得到容器中所有的BeanPostProcessor；挨个执行beforeInitialization，
 * 一但返回null，跳出for循环，不会执行后面的postProcessorsBeforeInitialization
 *
 * BeanPostProcessor原理:
 * 1、populateBean(beanName, mbd, instanceWrapper);给bean进行属性赋值
 * 2、initializeBean
 *     1）applyBeanPostProcessorsBeforeInitialization(wrappedBean, beanName);
 *     2）invokeInitMethods(beanName, wrappedBean, mbd);执行自定义初始化方法
 *     3）applyBeanPostProcessorsAfterInitialization(wrappedBean, beanName);
 *
 *
 * 1）、指定初始化和销毁方法；通过 @Bean 指定 init-method 和 destroy-method
 * 2）、通过让 Bean 实现 InitializingBean（定义初始化逻辑），DisposableBean（定义销毁逻辑）
 * 3）、可以使用JSR250
 * 		@PostConstruct：在bean创建完成并且属性赋值完成；来执行初始化方法
 * 		@PreDestroy：在容器销毁bean之前通知我们进行清理工作
 * 4）、BeanPostProcessor，两个主要方法如下：
 * 		postProcessBeforeInitialization:
 * 	        在初始化之前工作
 * 	        before InitializingBean#afterPropertiesSet or a custom init-method
 * 	        理解：在上面三者之前调用
 * 		postProcessAfterInitialization:
 * 		    在初始化之后工作
 * 		    after InitializingBean#afterPropertiesSet or a custom init-method
 * 		    理解：在上面三者之后调用
 *
 * Spring底层对 BeanPostProcessor 的使用；
 * 		bean赋值，注入其他组件，@Autowired，生命周期注解功能，@Async, xxxBeanPostProcessor;
 * 	    AutowiredAnnotationBeanPostProcessor
 */
@Configuration
@ComponentScan("com.cyh.bean")
public class MainConfigOfLifeCycle {

    @Scope("prototype")
    @Bean(initMethod = "init", destroyMethod = "destroy")
    public Car car() {
        return new Car();
    }
}
