package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.repositories.base.PropertyRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SqlPropertyRepositoryImpl implements PropertyRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Property getPropertyById(int id) {
        Property result = null;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            result = session.get(Property.class, id);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Property addNewProperty(Property newProperty) {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            session.save(newProperty);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return newProperty;
    }

    @Override
    public Property updateProperty(Property propertyToUpdate, int id) {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            Property property = getPropertyById(id);

            property.setRentPrice(propertyToUpdate.getRentPrice());
            property.setDescription(propertyToUpdate.getDescription());
            property.setRentPaid(propertyToUpdate.getIsRentPaid());

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return getPropertyById(id);
    }
}
