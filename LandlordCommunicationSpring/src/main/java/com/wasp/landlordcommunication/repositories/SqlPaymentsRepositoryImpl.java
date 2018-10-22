package com.wasp.landlordcommunication.repositories;

import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.models.Property;
import com.wasp.landlordcommunication.repositories.base.PaymentsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SqlPaymentsRepositoryImpl implements PaymentsRepository {

    private static final String USER_PARAMETER = "id";
    private static final String GET_BY_TENANT_ID_QUERY = "FROM Payment WHERE tenantId = :id";
    private static final String GET_BY_LANDLORD_ID_QUERY = "FROM Payment WHERE landlordId = :id";


    private final SessionFactory sessionFactory;

    @Autowired
    public SqlPaymentsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Payment makePayment(Payment newPayment) {
        Integer id = 0;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            id = (Integer) session.save(newPayment);
            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return getPaymentById(id);
    }

    @Override
    public Payment getPaymentById(int id) {

        Payment payment = null;
        try (Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            payment = session.get(Payment.class, id);
            transaction.commit();

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
        return payment;
    }

    @Override
    public List<Payment> getPaymentsByTenantId(int id) {
        List<Payment> payments = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            payments = session
                    .createQuery(GET_BY_TENANT_ID_QUERY, Payment.class)
                    .setParameter(USER_PARAMETER, id)
                    .list();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payments;
    }

    @Override
    public List<Payment> getPaymentsByLandlordId(int id) {
        List<Payment> payments = new ArrayList<>();

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            payments = session
                    .createQuery(GET_BY_LANDLORD_ID_QUERY, Payment.class)
                    .setParameter(USER_PARAMETER, id)
                    .list();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return payments;
    }
}
