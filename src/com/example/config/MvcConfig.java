package com.example.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.dao.SheetDao;
import com.example.dao.SheetDaoImpl;
import com.example.dao.UserDAO;
import com.example.dao.UserDAOImpl;
import com.example.model.Sheet;
import com.example.model.User;

@EnableWebMvc
@ComponentScan(basePackages = {"com.example"})
@Configuration
@EnableTransactionManagement
public class MvcConfig extends WebMvcConfigurerAdapter {
	
	
	public MvcConfig() {
	
		System.out.println("in mvc config contructor");
		
	}
 
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        
    }
 
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
 
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/expensesheets");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
     
        return dataSource;
    }
    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
     
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
     
        sessionBuilder.addAnnotatedClasses(User.class,Sheet.class);
        
        
        sessionBuilder.addProperties(getHibernateProperties());
     
        return sessionBuilder.buildSessionFactory();
    }
    
    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        
        return properties;
    }
    
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(
            SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(
                sessionFactory);
     
        return transactionManager;
    }
    
    @Autowired
    @Bean(name = "userDao")
    public UserDAO getUserDao(SessionFactory sessionFactory) {
        return new UserDAOImpl(sessionFactory);
    }
    
    @Autowired
    @Bean(name = "sheetDao")
    public SheetDao getSheetDao(SessionFactory sessionFactory) {
    	
    	return new SheetDaoImpl(sessionFactory);
    }
    
    
}