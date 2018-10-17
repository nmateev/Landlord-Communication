package com.wasp.landlordcommunication;

import com.wasp.landlordcommunication.models.*;
import com.wasp.landlordcommunication.models.templatemessages.TemplateMessage;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.mappers.TemplateMessageMapperImpl;
import com.wasp.landlordcommunication.utils.mappers.base.TemplateMessageMapper;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LandlordCommunicationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LandlordCommunicationApplication.class, args);
    }

    @Bean
    public SessionFactory sessionFactory() {
        return new Configuration()
                .configure(Constants.HIBERNATE_CONFIGURATION_FILE_NAME)
                .addAnnotatedClass(TemplateMessage.class)
                .addAnnotatedClass(ChatMessage.class)
                .addAnnotatedClass(ChatSession.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Property.class)
                .addAnnotatedClass(IssueReport.class)
                .addAnnotatedClass(Payment.class)
                .buildSessionFactory();
    }

    @Bean
    public TemplateMessageMapper getTemplateMessageMapper() {
        return new TemplateMessageMapperImpl();
    }
}
