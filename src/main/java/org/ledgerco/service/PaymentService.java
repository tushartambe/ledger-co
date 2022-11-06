package org.ledgerco.service;

import org.ledgerco.model.PaymentDetails;
import org.ledgerco.repository.Repository;

public class PaymentService {
    private final Repository repository;

    public PaymentService(Repository repository) {
        this.repository = repository;
    }

    public void recordPayment(String bank, PaymentDetails paymentDetails) {
        repository.addPayment(bank, paymentDetails);
    }

}
