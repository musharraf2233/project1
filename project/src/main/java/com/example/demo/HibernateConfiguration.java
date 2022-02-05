package com.example.demo;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan({"com"})
@PropertySource(value= {"classpath:application.properties"})
@EnableTransactionManagement
public class HibernateConfiguration {
	
	@Autowired
	private Environment environment;

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory=new LocalSessionFactoryBean();
		sessionFactory.setDataSource(getDataSource());
		sessionFactory.setPackagesToScan(new String[] {"com.model"});
		sessionFactory.setHibernateProperties(getHibernateProperties());
		return sessionFactory;
	}
	@Bean
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource=new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("spring.datasource.driver-class-name"));
		dataSource.setUrl(environment.getRequiredProperty("spring.datasource.url"));
		return dataSource;
	}
	
	public Properties getHibernateProperties() {
		Properties prop=new Properties();
		prop.put("hibernate.dialect", environment.getProperty("spring.jpa.properties.hibernate.dialect"));
		prop.put("hibernate.show_sql", environment.getProperty("spring.jpa.show-sql"));
		prop.put("hibernate.format_sql",true);
		prop.put("hibernate.connection.username",environment.getProperty("spring.datasource.username"));
		prop.put("hibernate.connection.password", environment.getProperty("spring.datasource.password"));
		prop.put("hibernate.hbm2ddl.auto",environment.getProperty("spring.jpa.hibernate.ddl-auto"));
		return prop;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager=new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}
}
