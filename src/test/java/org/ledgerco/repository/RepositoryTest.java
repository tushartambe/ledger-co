package org.ledgerco.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ledgerco.model.LoanDetails;
import org.ledgerco.model.PaymentDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    Repository repository;

    @BeforeEach
    void setUp() {
        repository = Repository.getRepository();
    }

    @Test
    void shouldCreateEmptyRepository() {
        assertNotNull(repository);
    }

    @Test
    void shouldReturnLoanDetailsFromRepository() {
        LoanDetails expectedLoanDetails = new LoanDetails("Elon musk", 1000, 1, 10);
        repository.addLoan("Some Big Bank", expectedLoanDetails);

        Optional<LoanDetails> actualLoanDetails = repository.getLoanDetails("Some Big Bank", "Elon musk");

        assertEquals(expectedLoanDetails, actualLoanDetails.get());
        assertNotEquals(Optional.empty(), actualLoanDetails);
    }

    @Test
    void shouldReturnPaymentDetailsFromRepository() {
        PaymentDetails expectedPaymentDetails = new PaymentDetails("Elon musk", 1000, 1);
        repository.addPayment("Some Big Bank", expectedPaymentDetails);

        Optional<PaymentDetails> actualPaymentDetails = repository.getPaymentDetails("Some Big Bank", "Elon musk");

        assertEquals(expectedPaymentDetails, actualPaymentDetails.get());
        assertNotEquals(Optional.empty(), actualPaymentDetails);
    }

    @Test
    void shouldReturnNothingIfLoanDetailsNotPresent() {
        Optional<LoanDetails> actualLoanDetails = repository.getLoanDetails("Some Other Big Bank", "Jeff bezos");

        assertEquals(Optional.empty(), actualLoanDetails);
    }

    @Test
    void shouldReturnNothingIfPaymentDetailsNotPresent() {
        Optional<PaymentDetails> actualPaymentDetails = repository.getPaymentDetails("Some Other Big Bank", "Jeff bezos");

        assertEquals(Optional.empty(), actualPaymentDetails);
    }
}