package com.wasp.landlordcommunication.services;

import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.repositories.base.PaymentsRepository;
import com.wasp.landlordcommunication.services.base.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentsServiceImpl implements PaymentsService {

    private final PaymentsRepository paymentsRepository;

    @Autowired
    public PaymentsServiceImpl(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    @Override
    public Payment makePayment(Payment newPayment) {
        return paymentsRepository.makePayment(newPayment);
    }

    @Override
    public Payment getPaymentById(int id) {
        return paymentsRepository.getPaymentById(id);
    }

    @Override
    public List<Payment> getPaymentsByTenantId(int id) {
        return paymentsRepository.getPaymentsByTenantId(id);
    }

    @Override
    public List<Payment> getPaymentsByLandlordId(int id) {
        return paymentsRepository.getPaymentsByLandlordId(id);
    }
}
