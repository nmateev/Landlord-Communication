package com.wasp.landlordcommunication;

import com.wasp.landlordcommunication.models.TemplateMessage;
import com.wasp.landlordcommunication.models.TemplateType;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LandlordcommunicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LandlordcommunicationApplication.class, args);
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(TemplateMessage.class)
                .addAnnotatedClass(TemplateType.class)
                .buildSessionFactory();

    }
}
