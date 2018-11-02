package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.rating.Rating;
import com.wasp.landlordcommunication.models.rating.RatingDTO;
import com.wasp.landlordcommunication.repositories.base.RatingsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class SqlRatingsRepositoryImpl implements RatingsRepository {
    private static final String GET_RATINGS_BY_USER_ID_QUERY = "FROM Rating WHERE votedForId = :votedFor";
    private static final String USER_ID_VOTER_PARAMETER = "voter";
    private static final String USER_ID_VOTED_FOR_PARAMETER = "votedFor";
    private static final String IS_USER_ALREADY_RATED_BY_USER_ID_QUERY = "FROM Rating WHERE voterId = :voter AND votedForId = :votedFor";


    private final SessionFactory sessionFactory;

    @Autowired
    public SqlRatingsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Rating addRating(Rating newRating) {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            session.save(newRating);
            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return newRating;
    }

    @Override
    public List<Rating> getRatingsByUserId(int id) {
        List<Rating> ratings = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            ratings = session
                    .createQuery(GET_RATINGS_BY_USER_ID_QUERY, Rating.class)
                    .setParameter(USER_ID_VOTED_FOR_PARAMETER, id)
                    .list();

            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ratings;
    }

    @Override
    public Rating isAlreadyRated(RatingDTO ratingDTO) {
        Rating rating = null;


        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            rating = session
                    .createQuery(IS_USER_ALREADY_RATED_BY_USER_ID_QUERY, Rating.class)
                    .setParameter(USER_ID_VOTER_PARAMETER, ratingDTO.getVoterId())
                    .setParameter(USER_ID_VOTED_FOR_PARAMETER, ratingDTO.getVotedForId())
                    .uniqueResult();

            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return rating;
    }
}
