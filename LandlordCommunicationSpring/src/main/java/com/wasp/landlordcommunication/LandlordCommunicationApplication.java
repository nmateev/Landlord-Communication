package com.wasp.landlordcommunication;

import com.wasp.landlordcommunication.models.ChatSessions;
import com.wasp.landlordcommunication.models.IssueReport;
import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.models.mappers.TemplateMessageMapperImpl;
import com.wasp.landlordcommunication.models.mappers.base.TemplateMessageMapper;
import com.wasp.landlordcommunication.models.properties.Property;
import com.wasp.landlordcommunication.models.templatemessages.TemplateMessage;
import com.wasp.landlordcommunication.utils.Constants;
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
                .addAnnotatedClass(Property.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(ChatSessions.class)
                .addAnnotatedClass(IssueReport.class)
                .addAnnotatedClass(Property.class)
                .buildSessionFactory();
    }

    @Bean
    public TemplateMessageMapper getTemplateMessageMapper() {
        return new TemplateMessageMapperImpl();
    }
}
