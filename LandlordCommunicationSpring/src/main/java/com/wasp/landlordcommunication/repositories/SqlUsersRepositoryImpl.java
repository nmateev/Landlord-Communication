package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.user.User;
import com.wasp.landlordcommunication.repositories.base.UsersRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class SqlUsersRepositoryImpl implements UsersRepository {

    private static final String GET_BY_USER_NAME_QUERY = "FROM User WHERE userName = :userName";
    private static final String GET_BY_USER_TYPE_QUERY = "FROM User WHERE userType = :userType";
    private static final String USER_NAME_PARAMETER = "userName";
    private static final String USER_TYPE_PARAMETER = "userType";
    private final SessionFactory sessionFactory;

    @Autowired
    public SqlUsersRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public User createUser(User userToCreate) {
        Integer id = 0;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            id = (Integer) session.save(userToCreate);
            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return getUserById(id);
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

    @Override
    public User getUserById(int userId) {
        User user = null;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            user = session.get(User.class, userId);

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public boolean isUserNameAvailable(String name) {
        User user = getUserByUserName(name);
        if (!Objects.equals(user, null)) {
            return false;
        }
        return true;
    }

    @Override
    public User updateUser(User userToUpdate, int userId) {
        User user = null;

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            user = session.get(User.class, userId);

            user.setUserPicture(userToUpdate.getUserPicture());
            user.setUserVoteCount(userToUpdate.getUserVoteCount());
            user.setUserVoteSum(userToUpdate.getUserVoteSum());
            user.setUserRating(userToUpdate.getUserRating());

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return user;
    }

    @Override
    public List<User> getUsersByType(String userType) {
        List<User> users = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            users = session
                    .createQuery(GET_BY_USER_TYPE_QUERY, User.class)
                    .setParameter(USER_TYPE_PARAMETER, userType)
                    .list();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
