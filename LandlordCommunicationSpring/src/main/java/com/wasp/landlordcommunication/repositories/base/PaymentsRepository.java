package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.Payment;

import java.util.List;

public interface PaymentsRepository {

    Payment makePayment(Payment newPayment);

    Payment getPaymentById(int id);

    List<Payment> getPaymentsByTenantId(int id);

    List<Payment> getPaymentsByLandlordId(int id);

}
