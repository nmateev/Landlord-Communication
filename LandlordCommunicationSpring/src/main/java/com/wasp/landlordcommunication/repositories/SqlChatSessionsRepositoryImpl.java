package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.chatsession.ChatSession;
import com.wasp.landlordcommunication.models.chatsession.ChatSessionDTO;
import com.wasp.landlordcommunication.repositories.base.ChatSessionsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class SqlChatSessionsRepositoryImpl implements ChatSessionsRepository {

    private static final String TENANT_PARAMETER = "tenantId";
    private static final String LANDLORD_PARAMETER = "landlordId";
    private static final String GET_CHAT_SESSION_BY_TENANT_AND_LANDLORD_QUERY = "FROM ChatSession WHERE tenantId = :tenantId AND landlordId = :landlordId";
    private static final String GET_CHAT_SESSIONS_BY_TENANT_QUERY = "FROM ChatSession WHERE tenantId = :tenantId";
    private static final String GET_CHAT_SESSIONS_BY_LANDLORD_QUERY = "FROM ChatSession WHERE landlordId = :landlordId";


    private final SessionFactory sessionFactory;

    @Autowired
    public SqlChatSessionsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public ChatSession createChatSession(ChatSession newChatSession) {

        Integer id = 0;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            id = (Integer) session.save(newChatSession);
            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return getChatSessionById(id);
    }

    @Override
    public ChatSession getChatSessionById(int chatSessionId) {
        ChatSession chatSession = null;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            chatSession = session.get(ChatSession.class, chatSessionId);

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return chatSession;
    }

    @Override
    public ChatSession getChatSessionByTenantAndLandlord(ChatSessionDTO chatSessionDTO) {
        ChatSession chatSession = null;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            chatSession = session
                    .createQuery(GET_CHAT_SESSION_BY_TENANT_AND_LANDLORD_QUERY, ChatSession.class)
                    .setParameter(TENANT_PARAMETER, chatSessionDTO.getTenantId())
                    .setParameter(LANDLORD_PARAMETER, chatSessionDTO.getLandlordId())
                    .uniqueResult();

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return chatSession;
    }

    @Override
    public boolean isChatSessionCreated(ChatSessionDTO chatSessionDTO) {

        ChatSession chatSession = null;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            chatSession = session
                    .createQuery(GET_CHAT_SESSION_BY_TENANT_AND_LANDLORD_QUERY, ChatSession.class)
                    .setParameter(TENANT_PARAMETER, chatSessionDTO.getTenantId())
                    .setParameter(LANDLORD_PARAMETER, chatSessionDTO.getLandlordId())
                    .uniqueResult();

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        if (Objects.equals(chatSession, null)) {
            return false;
        }
        return true;
    }

    @Override
    public List<ChatSession> getAllChatSessionsByTenant(int tenantId) {

        List<ChatSession> chatSessions = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<ChatSession> query = session.createQuery(GET_CHAT_SESSIONS_BY_TENANT_QUERY, ChatSession.class);
            query.setParameter(TENANT_PARAMETER, tenantId);
            chatSessions = query.list();

            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return chatSessions;
    }

    @Override
    public List<ChatSession> getAllChatSessionsByLandlord(int landlordId) {
        List<ChatSession> chatSessions = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<ChatSession> query = session.createQuery(GET_CHAT_SESSIONS_BY_LANDLORD_QUERY, ChatSession.class);
            query.setParameter(LANDLORD_PARAMETER, landlordId);
            chatSessions = query.list();

            transaction.commit();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return chatSessions;
    }
}
