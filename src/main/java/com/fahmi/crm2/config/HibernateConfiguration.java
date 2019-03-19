package com.fahmi.crm2.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfiguration {

    @Value("${pool.maxPoolSize}")
    private int maxPoolSize;

    /*
     * PropertySourcesPlaceHolderConfigurer Bean only required for @Value("{}") annotations.
     * Remove this bean if you are not using @Value annotations for injecting properties.
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /*
     * LocalSessionFactoryBean is a FactoryBean<SessionFactory>. It means that it allows creating instances of SessionFactory.
     * The SessionFactory can then be passed to Hibernate-based data access objects via dependency injection.
     */
    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setPackagesToScan("com.fahmi.crm2.entities"); //Specify packages to search for autodetection of your entity classes in the classpath. This way there is no need for a hibernate mapping file.

        return sessionFactory;
    }

    // BasicDataSource is a basic implementation of javax.sql.DataSource that is configured via JavaBeans properties.
    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/web_customer_tracker");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        dataSource.setInitialSize(5);
        dataSource.setMaxTotal(maxPoolSize);
        dataSource.setMinIdle(5);
        dataSource.setMinIdle(2);

        return dataSource;
    }

    /*
     * The Properties class represents a persistent set of properties.
     * The Properties can be saved to a stream or loaded from a stream.
     * Each key and its corresponding value in the property list is a string.
     *
     * This class is thread-safe: multiple threads can share a single Properties object without the need for external synchronization.
     */
    @Bean
    public Properties hibernateProperties(){
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("show-sql", "true");

        return hibernateProperties;
    }
}
