package com.cyh.tx;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan("com.cyh.tx")
@EnableTransactionManagement
public class TxConfig {

    @Bean
    public DataSource dataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("root");
        dataSource.setDriverClass("com.mysql.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/cyhtest");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws PropertyVetoException {
        return new DataSourceTransactionManager(dataSource());
    }

}

/**
 * 环境搭建：
 * 1、导入相关依赖
 * 		数据源、数据库驱动、Spring-jdbc模块
 * 2、配置数据源、JdbcTemplate（Spring提供的简化数据库操作的工具）操作数据
 * 3、给方法上标注 @Transactional 表示当前方法是一个事务方法
 * 4、@EnableTransactionManagement 开启基于注解的事务管理功能
 * 5、配置事务管理器来控制事务
 * 		@Bean
 * 		public PlatformTransactionManager transactionManager()
 *
 *
 * 原理：
 * 1）、@EnableTransactionManagement
 * 		利用 TransactionManagementConfigurationSelector 给容器中会导入组件
 * 		导入两个组件
 * 		AutoProxyRegistrar
 * 		ProxyTransactionManagementConfiguration
 * 2）、AutoProxyRegistrar
 * 		给容器中注册一个 InfrastructureAdvisorAutoProxyCreator 组件，这是一个 SmartInstantiationAwareBeanPostProcessor
 * 		利用后置处理器机制在对象创建以后，包装对象，返回一个代理对象（增强器），代理对象执行方法利用拦截器链进行调用
 * 3）、ProxyTransactionManagementConfiguration 做了什么
 * 		给容器中注册事务增强器
* 		1）、事务增强器要用事务注解的信息，AnnotationTransactionAttributeSource 解析事务注解
* 		2）、事务拦截器：
* 			TransactionInterceptor；保存了事务属性信息，事务管理器
* 			他是一个 MethodInterceptor，在目标方法执行的时候，执行拦截器链
* 			而此处的拦截器链只有一个，即事务拦截器：
* 				1）、先获取事务相关的属性
* 				2）、再获取 PlatformTransactionManager，如果事先没有添加任何 transactionManager
* 					最终会从容器中按照类型获取一个 PlatformTransactionManager
* 				3）、执行目标方法
* 					如果异常，获取到事务管理器，利用事务管理回滚操作；
* 					如果正常，利用事务管理器，提交事务
 */
