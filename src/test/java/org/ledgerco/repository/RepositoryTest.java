package org.ledgerco.repository;

import org.junit.jupiter.api.Test;
import org.ledgerco.model.LoanDetails;
import org.ledgerco.model.PaymentDetails;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    @Test
    void shouldCreateEmptyRepository() {
        Repository repository = new Repository(new HashMap<>(), new HashMap<>());
        assertNotNull(repository);
    }

    @Test
    void shouldReturnLoanDetailsFromRepository() {
        Repository repository = new Repository(new HashMap<>(), new HashMap<>());
        LoanDetails expectedLoanDetails = new LoanDetails("Elon musk", 1000, 1, 10);
        repository.addLoan("Some Big Bank", expectedLoanDetails);

        Optional<LoanDetails> actualLoanDetails = repository.getLoanDetails("Some Big Bank", "Elon musk");

        assertEquals(expectedLoanDetails, actualLoanDetails.get());
        assertNotEquals(Optional.empty(), actualLoanDetails);
    }

    @Test
    void shouldReturnPaymentDetailsFromRepository() {
        Repository repository = new Repository(new HashMap<>(), new HashMap<>());
        PaymentDetails expectedPaymentDetails = new PaymentDetails("Elon musk", 1000, 1);
        repository.addPayment("Some Big Bank", expectedPaymentDetails);

        Optional<PaymentDetails> actualPaymentDetails = repository.getPaymentDetails("Some Big Bank", "Elon musk");

        assertEquals(expectedPaymentDetails, actualPaymentDetails.get());
        assertNotEquals(Optional.empty(), actualPaymentDetails);
    }

    @Test
    void shouldReturnNothingIfLoanDetailsNotPresent() {
        Repository repository = new Repository(new HashMap<>(), new HashMap<>());

        Optional<LoanDetails> actualLoanDetails = repository.getLoanDetails("Some Big Bank", "Elon musk");

        assertEquals(Optional.empty(), actualLoanDetails);
    }

    @Test
    void shouldReturnNothingIfPaymentDetailsNotPresent() {
        Repository repository = new Repository(new HashMap<>(), new HashMap<>());

        Optional<PaymentDetails> actualPaymentDetails = repository.getPaymentDetails("Some Big Bank", "Elon musk");

        assertEquals(Optional.empty(), actualPaymentDetails);
    }
}