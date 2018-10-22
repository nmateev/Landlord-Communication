package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.ChatMessage;

import com.wasp.landlordcommunication.repositories.base.ChatMessageRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SqlChatMessageRepositoryImpl implements ChatMessageRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ChatMessage getChatMessageById(int id) {
        ChatMessage result = null;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            result = session.get(ChatMessage.class, id);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ChatMessage postChatMessage(ChatMessage newMessage) {
        try (Session session = sessionFactory.openSession()) {

          Transaction transaction=session.beginTransaction();
            session.save(newMessage);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newMessage;
    }
}
