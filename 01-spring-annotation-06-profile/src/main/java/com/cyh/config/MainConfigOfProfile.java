package com.cyh.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import com.cyh.bean.Yellow;
import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @Profile：指定组件在哪个环境的情况下才能被注册到容器中，不指定，任何环境下都能注册这个组件
 *
 * 1）、加了环境标识的 bean 只有这个环境被激活的时候才能注册到容器中。默认是 default 环境
 * 2）、写在配置类上，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
 * 3）、没有标注环境标识的bean在任何环境下都是加载的
 */
//@Profile("test") // 只有当环境为test的时候整个配置类才生效
@Configuration
@PropertySource("classpath:/dbconfig.properties")
public class MainConfigOfProfile implements EmbeddedValueResolverAware {

    // 方式一
    @Value("${db.user}")
    private String username;
    private String driverClass;

    /**
     * 没有加 @Profile 所以在任何环境下都生效
     */
    @Bean
    public Yellow yellow() {
        return new Yellow();
    }

    /**
     * 方式二：在方法入参上使用 @Value
     */
    @Profile("test")
    @Bean
    public DataSource dataSourceTest(@Value("${db.password}") String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/cyhtest");
        return dataSource;
    }

    @Profile("dev")
    @Bean
    public DataSource dataSourceDev(@Value("${db.password}") String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/cyhdev");
        return dataSource;
    }

    @Profile("prod")
    @Bean
    public DataSource dataSourceProd(@Value("${db.password}") String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(password);
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/cyhprod");
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        // 方法三
        driverClass = resolver.resolveStringValue("${db.driverClass}");
    }
}
