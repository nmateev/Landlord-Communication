package com.wasp.landlordcommunication;

import com.wasp.landlordcommunication.models.Payment;
import com.wasp.landlordcommunication.repositories.base.PaymentsRepository;
import com.wasp.landlordcommunication.services.PaymentsServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PaymentsServiceImplTests {
    @Mock
    PaymentsRepository mockRepository;

    @InjectMocks
    PaymentsServiceImpl service;

    List<Payment> defaultTestInput = Arrays.asList(
            new Payment(
                    1,
                    1,
                    "Address1",
                    1,
                    1000.00,
                    "01.01.2019",
                    "123456789"),
            new Payment(
                    2,
                    2,
                    "Address2",
                    2,
                    2000.00,
                    "02.02.2019",
                    "987654321"),
            new Payment(
                    3,
                    3,
                    "Address3",
                    3,
                    3000.00,
                    "03.03.2019",
                    "135792468"));


    @Test
    public void makePayment_Should_ReturnNewPayment() {
        // Arrange
        Payment newPayment = new Payment();
        Mockito.when(mockRepository.makePayment(newPayment))
                .thenReturn(newPayment);

        // Act
        Payment payment = service.makePayment(newPayment);

        // Assert
        Assert.assertEquals(payment, newPayment);
    }

    @Test
    public void getPaymentById_Should_ReturnMatchingPayment_WhenMatchExist() {
        // Arrange
        Mockito.when(mockRepository.getPaymentById(1))
                .thenReturn(defaultTestInput.get(0));

        // Act
        Payment result = service.getPaymentById(1);

        // Assert
        Assert.assertEquals(result, defaultTestInput.get(0));
    }

    @Test
    public void getPaymentsByTenantId_Should_ReturnMatchingPayment_WhenMatchExist() {
        // Arrange
        Mockito.when(mockRepository.getPaymentsByTenantId(2))
                .thenReturn(defaultTestInput);

        // Act
        List<Payment> result = service.getPaymentsByTenantId(2);

        // Assert
        Assert.assertEquals(result, defaultTestInput);
    }

    @Test
    public void getPaymentsByLandlordId_Should_ReturnMatchingPayment_WhenMatchExist() {
        // Arrange
        Mockito.when(mockRepository.getPaymentsByLandlordId(3))
                .thenReturn(defaultTestInput);

        // Act
        List<Payment> result = service.getPaymentsByLandlordId(3);

        // Assert
        Assert.assertEquals(result, defaultTestInput);
    }
}
