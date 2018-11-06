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
import java.util.Date;
import java.util.List;

@Repository
public class SqlChatMessagesRepositoryImpl implements ChatMessagesRepository {

    private static final String GET_MESSAGES_BY_CHAT_SESSION_ID_QUERY = "FROM ChatMessage WHERE chatSessionId = :chatSessionId AND dateSent >= :dateSearched";
    private static final String GET_TENANTS_UNDELIVERED_MESSAGES_QUERY = "FROM ChatMessage WHERE dateSent >= :dateSearched AND chatSessionId = :chatSessionId  AND isDeliveredToTenant = :isDeliveredToTenant";
    private static final String GET_LANDLORDS_UNDELIVERED_MESSAGES_QUERY = "FROM ChatMessage WHERE dateSent >= :dateSearched AND chatSessionId = :chatSessionId  AND isDeliveredToLandlord = :isDeliveredToLandlord";
    private static final String DATE_SEARCHED_PARAMETER = "dateSearched";
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

        Date dateThreeMonthsBackFromNow = dateFormatter.getDateThreeMonthsBackFromNow();

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            messages = session
                    .createQuery(GET_MESSAGES_BY_CHAT_SESSION_ID_QUERY, ChatMessage.class)
                    .setParameter(CHAT_SESSION_ID_PARAMETER, id)
                    .setParameter(DATE_SEARCHED_PARAMETER, dateThreeMonthsBackFromNow)
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

        Date dateThreeMonthsBackFromNow = dateFormatter.getDateThreeMonthsBackFromNow();

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            undeliveredMessages = session
                    .createQuery(GET_TENANTS_UNDELIVERED_MESSAGES_QUERY, ChatMessage.class)
                    .setParameter(DATE_SEARCHED_PARAMETER, dateThreeMonthsBackFromNow)
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

        Date dateThreeMonthsBackFromNow = dateFormatter.getDateThreeMonthsBackFromNow();

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            undeliveredMessages = session
                    .createQuery(GET_LANDLORDS_UNDELIVERED_MESSAGES_QUERY, ChatMessage.class)
                    .setParameter(DATE_SEARCHED_PARAMETER, dateThreeMonthsBackFromNow)
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

            chatMessage.setIsDeliveredToTenant(messageToUpdate.isDeliveredToTenant());
            chatMessage.setIsDeliveredToLandlord(messageToUpdate.isDeliveredToLandlord());
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return chatMessage;
    }
}
