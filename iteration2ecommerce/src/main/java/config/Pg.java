package config;
import service.CustomerService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import entity.Config;


public class Pg {
    
    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext(Config.class);
        appContext.scan("service");

        CustomerService customerService = (CustomerService)appContext.getBean(CustomerService.class);
        customerService.test();

        appContext.close();
        
    }

}