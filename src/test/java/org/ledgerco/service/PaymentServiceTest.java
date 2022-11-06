package org.ledgerco.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ledgerco.model.PaymentDetails;
import org.ledgerco.repository.Repository;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    Repository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(Repository.class);
    }

    @Test
    void shouldAddPaymentToRepository() {
        PaymentService paymentService = new PaymentService(repository);
        PaymentDetails paymentDetails = new PaymentDetails("Elon musk", 1000, 1);

        doNothing().when(repository).addPayment(any(), any());

        paymentService.process("Big Bank", paymentDetails);

        verify(repository, times(1)).addPayment("Big Bank", paymentDetails);
    }
}