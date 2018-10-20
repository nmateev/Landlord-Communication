package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.ChatMessage;
import com.wasp.landlordcommunication.repositories.base.ChatMessagesRepository;
import com.wasp.landlordcommunication.utils.DateFormatter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SqlChatMessagesRepositoryImpl implements ChatMessagesRepository {

    private static final String GET_MESSAGES_BY_CHAT_SESSION_ID_QUERY = "FROM ChatMessage WHERE chatSessionId = :chatSessionId";
    private static final String GET_UNDELIVERED_MESSAGES_FIRST_PART_QUERY = "FROM ChatMessage WHERE dateSent >= \'";
    private static final String GET_TENANTS_UNDELIVERED_MESSAGES_SECOND_PART_QUERY = "\' AND dateSent<= NOW() AND chatSessionId = :chatSessionId  AND isDeliveredToTenant = :isDeliveredToTenant";
    private static final String GET_LANDLORDS_UNDELIVERED_MESSAGES_SECOND_PART_QUERY = "\' AND dateSent<= NOW() AND chatSessionId = :chatSessionId  AND isDeliveredToLandlord = :isDeliveredToLandlord";

    private static final String CHAT_SESSION_ID_PARAMETER = "chatSessionId";
    private static final String IS_MESSAGE_DELIVERED_TO_TENANT_PARAMETER = "isDeliveredToTenant";
    private static final String IS_MESSAGE_DELIVERED_TO_LANDLORD_PARAMETER = "isDeliveredToLandlord";


    private final SessionFactory sessionFactory;
    private final DateFormatter dateFormatter;

    @Autowired
    public SqlChatMessagesRepositoryImpl(SessionFactory sessionFactory, DateFormatter dateFormatter) {
        this.sessionFactory = sessionFactory;
        this.dateFormatter = dateFormatter;
    }

    @Override
    public List<ChatMessage> getMessagesByChatSessionId(int id) {
        List<ChatMessage> messages = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            messages = session
                    .createQuery(GET_MESSAGES_BY_CHAT_SESSION_ID_QUERY, ChatMessage.class)
                    .setParameter(CHAT_SESSION_ID_PARAMETER, id)
                    .list();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }

    @Override
    public List<ChatMessage> getTenantsUndeliveredMessagesByChatSessionId(int id) {
        List<ChatMessage> undeliveredMessages = new ArrayList<>();

        String dateThreeMonthsBackFromNow = dateFormatter.getDateThreeMonthsBackFromNow();

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            undeliveredMessages = session
                    .createQuery(GET_UNDELIVERED_MESSAGES_FIRST_PART_QUERY +
                                    dateThreeMonthsBackFromNow +
                                    GET_TENANTS_UNDELIVERED_MESSAGES_SECOND_PART_QUERY
                            , ChatMessage.class)
                    .setParameter(CHAT_SESSION_ID_PARAMETER, id)
                    .setParameter(IS_MESSAGE_DELIVERED_TO_TENANT_PARAMETER, false)
                    .list();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return undeliveredMessages;
    }

    @Override
    public List<ChatMessage> getLandlordsUndeliveredMessagesByChatSessionId(int id) {
        List<ChatMessage> undeliveredMessages = new ArrayList<>();

        String dateThreeMonthsBackFromNow = dateFormatter.getDateThreeMonthsBackFromNow();

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            undeliveredMessages = session
                    .createQuery(GET_UNDELIVERED_MESSAGES_FIRST_PART_QUERY +
                                    dateThreeMonthsBackFromNow +
                                    GET_LANDLORDS_UNDELIVERED_MESSAGES_SECOND_PART_QUERY
                            , ChatMessage.class)
                    .setParameter(CHAT_SESSION_ID_PARAMETER, id)
                    .setParameter(IS_MESSAGE_DELIVERED_TO_LANDLORD_PARAMETER, false)
                    .list();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return undeliveredMessages;
    }

    @Override
    public ChatMessage postChatMessage(ChatMessage newMessage) {
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();
            session.save(newMessage);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newMessage;
    }

    @Override
    public ChatMessage updateChatMessage(ChatMessage messageToUpdate) {
        ChatMessage chatMessage = null;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            chatMessage = session.get(ChatMessage.class, messageToUpdate.getMessageId());

            chatMessage.setDeliveredToTenant(messageToUpdate.isDeliveredToTenant());
            chatMessage.setDeliveredToLandlord(messageToUpdate.isDeliveredToLandlord());
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return chatMessage;
    }
}
