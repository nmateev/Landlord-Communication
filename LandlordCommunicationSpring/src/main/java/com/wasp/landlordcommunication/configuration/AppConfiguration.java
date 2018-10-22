package com.wasp.landlordcommunication.configuration;

import com.wasp.landlordcommunication.models.ChatMessage;
import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.models.chatsession.ChatSession;
import com.wasp.landlordcommunication.models.templatemessage.TemplateMessage;
import com.wasp.landlordcommunication.models.user.User;
import com.wasp.landlordcommunication.utils.Constants;
import com.wasp.landlordcommunication.utils.DateFormatter;
import com.wasp.landlordcommunication.utils.DateFormatterImpl;
import com.wasp.landlordcommunication.utils.mappers.TemplateMessageMapperImpl;
import com.wasp.landlordcommunication.utils.mappers.UserMapperImpl;
import com.wasp.landlordcommunication.utils.mappers.base.TemplateMessageMapper;
import com.wasp.landlordcommunication.utils.mappers.base.UserMapper;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@Configuration
public class AppConfiguration {

    @PostConstruct
    public void configure() {
        TimeZone.setDefault(TimeZone.getTimeZone(Constants.TIME_ZONE_CONFIGURATION_VALUE));
    }

    @Bean
    public SessionFactory provideSessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure(Constants.HIBERNATE_CONFIGURATION_FILE_NAME)
                .addAnnotatedClass(TemplateMessage.class)
                .addAnnotatedClass(ChatMessage.class)
                .addAnnotatedClass(ChatSession.class)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Property.class)
                .addAnnotatedClass(Payment.class)
                .buildSessionFactory();
    }

    @Bean
    public TemplateMessageMapper provideTemplateMessageMapper() {
        return new TemplateMessageMapperImpl();
    }

    @Bean
    public UserMapper provideUserMapper() {
        return new UserMapperImpl();
    }

    @Bean
    public DateFormatter provideDateFormatter() {
        return new DateFormatterImpl();
    }

    @Bean
    public PasswordEncoder providePasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
