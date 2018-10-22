package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.repositories.base.PropertiesRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SqlPropertiesRepositoryImpl implements PropertiesRepository {
    private static final String TENANT_PARAMETER = "tenantId";
    private static final String LANDLORD_PARAMETER = "landlordId";
    private static final String GET_BY_TENANT_ID_QUERY = "FROM Property WHERE tenantId = :tenantId";
    private static final String GET_BY_LANDLORD_ID_QUERY = "FROM Property WHERE landlordId = :landlordId";

    private final SessionFactory sessionFactory;

    @Autowired
    public SqlPropertiesRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Property getPropertyById(int id) {
        Property property = null;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            property = session.get(Property.class, id);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return property;
    }

    @Override
    public List<Property> getPropertiesByTenant(int tenantId) {
        List<Property> properties = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();


            properties = session
                    .createQuery(GET_BY_TENANT_ID_QUERY, Property.class)
                    .setParameter(TENANT_PARAMETER, tenantId)
                    .list();


            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }

    @Override
    public List<Property> getPropertiesByLandlord(int landlordId) {
        List<Property> properties = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            properties = session
                    .createQuery(GET_BY_LANDLORD_ID_QUERY, Property.class)
                    .setParameter(LANDLORD_PARAMETER, landlordId)
                    .list();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return properties;
    }

    @Override
    public Property addNewProperty(Property newProperty) {
        Integer id = 0;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            id = (Integer) session.save(newProperty);
            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return getPropertyById(id);
    }

    @Override
    public Property updateProperty(Property propertyToUpdate, int id) {

        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            Property property = session.get(Property.class, id);
            property.setTenantId(propertyToUpdate.getTenantId());
            property.setRentPrice(propertyToUpdate.getRentPrice());
            property.setDescription(propertyToUpdate.getDescription());
            property.setRentPaid(propertyToUpdate.getRentPaid());

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return getPropertyById(id);
    }
}
