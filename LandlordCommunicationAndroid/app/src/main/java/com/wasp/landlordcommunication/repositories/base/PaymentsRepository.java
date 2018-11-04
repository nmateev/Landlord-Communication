package com.wasp.landlordcommunication.repositories.base;

import com.wasp.landlordcommunication.models.Payment;

import java.io.IOException;
import java.util.List;

public interface PaymentsRepository {

    List<Payment> getAllPaymentsById(String userType, int id) throws IOException;

    Payment makePayment(Payment payment) throws IOException;
}
