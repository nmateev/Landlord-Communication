package com.wasp.landlordcommunication.services.base;

import com.wasp.landlordcommunication.models.Payment;

import java.io.IOException;
import java.util.List;

public interface PaymentsService {

    List<Payment> getAllPayments() throws IOException;
}
