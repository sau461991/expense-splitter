package com.example.initializers;



import javax.servlet.ServletContext;  
import javax.servlet.ServletException;  
import javax.servlet.ServletRegistration.Dynamic;  
  
import org.springframework.web.WebApplicationInitializer;  
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;  
import org.springframework.web.servlet.DispatcherServlet;

import com.example.config.*;  
  
public class WebAppInitializer implements WebApplicationInitializer {

	public void onStartup(ServletContext servletContext) throws ServletException {  
		
		System.out.println("in web app initializer");
          
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();  
        ctx.register(MvcConfig.class);  
        ctx.setServletContext(servletContext);    
        
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));  
        servlet.addMapping("/");  
        servlet.setLoadOnStartup(1);  
          
    }  

}
