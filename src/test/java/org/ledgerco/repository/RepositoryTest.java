package org.ledgerco.repository;

import org.junit.jupiter.api.Test;
import org.ledgerco.model.LoanDetails;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    @Test
    void shouldCreateEmptyRepository() {
        Repository repository = Repository.getRepository();
        assertNotNull(repository);
    }

    @Test
    void shouldAddLoanDetailsToRepository() {
        Repository repository = Repository.getRepository();
        LoanDetails expectedLoanDetails = new LoanDetails("Elon musk", 1000, 1, 10);
        repository.addLoan("Some Big Bank", expectedLoanDetails);

        LoanDetails actualLoanDetails = repository.getLoanDetails("Some Big Bank", "Elon musk");
        assertEquals(expectedLoanDetails, actualLoanDetails);
    }
}