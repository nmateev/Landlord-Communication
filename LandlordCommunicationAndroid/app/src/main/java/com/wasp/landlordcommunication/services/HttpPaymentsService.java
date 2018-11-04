package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.repositories.base.PaymentsRepository;
import com.wasp.landlordcommunication.services.base.PaymentsService;

import java.io.IOException;
import java.util.List;

public class HttpPaymentsService implements PaymentsService {

    private final PaymentsRepository mPaymentsRepository;

    public HttpPaymentsService(PaymentsRepository paymentsRepository) {
        mPaymentsRepository = paymentsRepository;
    }


    @Override
    public List<Payment> getAllPaymentsById(String userType, int id) throws IOException {

        return mPaymentsRepository.getAllPaymentsById(userType, id);
    }

    @Override
    public Payment makePayment(Payment payment) throws IOException {
        return mPaymentsRepository.makePayment(payment);
    }
}
