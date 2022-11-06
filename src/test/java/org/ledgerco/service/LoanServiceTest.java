package org.ledgerco.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ledgerco.model.LoanDetails;
import org.ledgerco.repository.Repository;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LoanServiceTest {

    Repository repository;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(Repository.class);
    }

    @Test
    void shouldAddLoanToRepository() {
        LoanService loanService = new LoanService(repository);
        LoanDetails loanDetails = new LoanDetails("Elon musk", 1000, 1, 10);

        doNothing().when(repository).addLoan(any(), any());

        loanService.recordLoan("Big Bank", loanDetails);

        verify(repository, times(1)).addLoan("Big Bank", loanDetails);
    }
}