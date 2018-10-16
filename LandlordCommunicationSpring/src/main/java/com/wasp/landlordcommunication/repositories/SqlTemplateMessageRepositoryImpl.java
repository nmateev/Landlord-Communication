package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.TemplateMessage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SqlTemplateMessageRepositoryImpl implements TemplateMessageRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TemplateMessage> getByMessageType(String template_type) {
        List<TemplateMessage> result = new ArrayList<>();
        try (
                Session session = sessionFactory.openSession();
        ) {
            session.beginTransaction();
            result = session.createQuery("from template_messages where template_type= :template_type")
                    .setParameter("template_type", template_type)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
