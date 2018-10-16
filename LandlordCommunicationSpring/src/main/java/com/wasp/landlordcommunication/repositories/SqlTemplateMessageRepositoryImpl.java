package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.templatemessages.TemplateMessage;
import com.wasp.landlordcommunication.repositories.base.TemplateMessageRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SqlTemplateMessageRepositoryImpl implements TemplateMessageRepository {
    private static final String TEMPLATE_TYPE_PARAMETER = "templateType";
    private static final String GET_BY_TEMPLATE_TYPE_QUERY = "FROM TemplateMessage WHERE templateType = :templateType";
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<TemplateMessage> getByTemplateType(String templateType) {

        List<TemplateMessage> result = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();


            result = session
                    .createQuery(GET_BY_TEMPLATE_TYPE_QUERY, TemplateMessage.class)
                    .setParameter(TEMPLATE_TYPE_PARAMETER, templateType)
                    .list();


            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result;
    }
}
