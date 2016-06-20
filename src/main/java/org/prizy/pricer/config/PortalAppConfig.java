package org.prizy.pricer.config;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@PropertySource("classpath:portal.properties")
public class PortalAppConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("mysql.jdbc.driver"));
        dataSource.setUrl(env.getProperty("mysql.jdbc.url"));
        dataSource.setUsername(env.getProperty("mysql.jdbc.username"));
        dataSource.setPassword(env.getProperty("mysql.jdbc.password"));
        return dataSource;
    }


    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
    
    @Bean
    @Autowired
    public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory)
    {
        HibernateTemplate hibernateTemplate = new HibernateTemplate(sessionFactory);
        return hibernateTemplate;
    }
        
    @Bean
    public LocalSessionFactoryBean sessionFactory()
    {
    	LocalSessionFactoryBean asfb = new LocalSessionFactoryBean();
        asfb.setDataSource(dataSource());
        asfb.setHibernateProperties(getHibernateProperties());        
        asfb.setPackagesToScan(new String[]{"org.prizy.pricer.domain"});
        return asfb;
    }

    @Bean
    public Properties getHibernateProperties()
    {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        properties.put("hibernate.globally_quoted_identifiers", "true");
        
        return properties;
    }
    @Bean
    public HibernateTransactionManager transactionManager()
    {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory().getObject());
        return txManager;
    }
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation()
    {
        return new PersistenceExceptionTranslationPostProcessor();
    }

}
