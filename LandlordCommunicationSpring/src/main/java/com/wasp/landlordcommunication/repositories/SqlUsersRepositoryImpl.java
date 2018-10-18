package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.User;
import com.wasp.landlordcommunication.repositories.base.UsersRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Repository
public class SqlUsersRepositoryImpl implements UsersRepository {

    private static final String GET_BY_USER_NAME_QUERY = "FROM User WHERE userName = :userName";
    private static final String USER_NAME_PARAMETER = "userName";
    private final SessionFactory sessionFactory;

    @Autowired
    public SqlUsersRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public User createUser(User userToCreate) {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            session.save(userToCreate);
            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return getUserByUserName(userToCreate.getUserName());
    }

    @Override
    public User getUserByUserName(String userName) {

        User user = null;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            user = session
                    .createQuery(GET_BY_USER_NAME_QUERY, User.class)
                    .setParameter(USER_NAME_PARAMETER, userName)
                    .uniqueResult();

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return user;
    }
}
