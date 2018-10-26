package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.repositories.base.Repository;
import com.wasp.landlordcommunication.services.base.PaymentsService;

import java.io.IOException;
import java.util.List;

public class HttpPaymentsService implements PaymentsService {

    private final Repository<Payment> mPaymentsRepository;

    public HttpPaymentsService(Repository<Payment> paymentsRepository) {
        mPaymentsRepository = paymentsRepository;
    }

    @Override
    public List<Payment> getAllPayments() throws IOException {
        return mPaymentsRepository.getAll();
    }
}
