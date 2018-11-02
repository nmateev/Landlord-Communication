package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.repositories.base.Repository;
import com.wasp.landlordcommunication.services.base.PaymentsService;
import com.wasp.landlordcommunication.utils.Constants;

import java.io.IOException;
import java.util.List;

public class HttpPaymentsService implements PaymentsService {

    private final Repository<Payment> mPaymentsRepository;

    public HttpPaymentsService(Repository<Payment> paymentsRepository) {
        mPaymentsRepository = paymentsRepository;
    }


    @Override
    public List<Payment> getAllPayments(String userType, int id) throws IOException {
        String parameter = userType.toLowerCase() + Constants.SLASH_STRING_VALUE + String.valueOf(id);
        return mPaymentsRepository.getAllByParameter(parameter);
    }

    @Override
    public Payment makeTransaction(Payment payment) throws IOException {
        return mPaymentsRepository.add(payment);
    }
}
