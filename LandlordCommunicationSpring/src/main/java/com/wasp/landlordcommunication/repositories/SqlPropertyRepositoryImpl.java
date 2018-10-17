package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.properties.Property;
import com.wasp.landlordcommunication.repositories.base.PropertyRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SqlPropertyRepositoryImpl implements PropertyRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Property> getAllProperties() {
        List<Property> result = new ArrayList<>();
        try (
                Session session = sessionFactory.openSession();
        ) {
            session.beginTransaction();
            result = session.createQuery("from Property ").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;

    }

    @Override
    public Property getPropertyById(int id) {
        Property result=null;
        try (
                Session session = sessionFactory.openSession();
        ) {
            session.beginTransaction();
             result = session.get(Property.class,id);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
        return result;
    }
}
