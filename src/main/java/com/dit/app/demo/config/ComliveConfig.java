package com.dit.app.demo.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import java.beans.PropertyVetoException;
import java.time.ZoneId;
import java.util.Properties;

@Configuration
@EnableJpaRepositories
@PropertySource("classpath:application.properties")
@ComponentScan("com.dit.app")
public class ComliveConfig {

    @Autowired
    private Environment env;

    @Bean (name = "modelMapper")
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean(name = "zoneId")
    public ZoneId zoneId()
    {
        ZoneId zoneId = ZoneId.of(env.getProperty("zoneId"));
        return zoneId;
    }
    @Bean
    public ComboPooledDataSource dataSource(){
        ComboPooledDataSource dataSource = new ComboPooledDataSource();

        try {
            dataSource.setDriverClass(env.getProperty("db.driverClassName"));
            dataSource.setJdbcUrl(env.getProperty("db.url"));
            dataSource.setUser(env.getProperty("db.username"));
            dataSource.setPassword(env.getProperty("db.password"));
            dataSource.setMinPoolSize(Integer.parseInt(env.getProperty("db.minPoolSize")));
            dataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("db.maxPoolSize")));
            dataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("db.maxIdleTime")));
            dataSource.setMaxStatements(Integer.parseInt(env.getProperty("db.maxStatements")));
            dataSource.setMaxStatementsPerConnection(Integer.parseInt(env.getProperty("db.maxStatementsPerConnection")));
            dataSource.setMaxIdleTimeExcessConnections(10000);
            dataSource.setTestConnectionOnCheckin(Boolean.parseBoolean(env.getProperty("db.testConnection")));

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return dataSource;
    }
    @Primary
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setDatabase(Database.MYSQL);

        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan("com.dit.app");
        entityManagerFactoryBean.setJpaProperties(hibProperties());
        entityManagerFactoryBean.afterPropertiesSet();

        return entityManagerFactoryBean.getObject();
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    private Properties hibProperties() {
        Properties properties = new Properties();
        //properties.put("hibernate.connection.driver_class",env.getProperty("db.driverClassName"));
        //properties.put("hibernate.connection.url",env.getProperty("db.url"));
        //properties.put("hibernate.connection.username",env.getProperty("db.username"));
        //properties.put("hibernate.connection.password",env.getProperty("db.password"));
        properties.put("hibernate.dialect", env.getProperty("db.hibernate.dialect"));
        properties.put("hibernate.show_sql", env.getProperty("db.hibernate.show_sql"));
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("db.hibernate.ddl-auto"));
        return properties;
    }
}
