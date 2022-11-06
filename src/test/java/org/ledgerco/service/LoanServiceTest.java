package org.ledgerco.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.ledgerco.model.LoanDetails;
import org.ledgerco.repository.Repository;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.matchers.Any;
import org.mockito.internal.matchers.Matches;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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

        loanService.process("Big Bank", loanDetails);

        verify(repository, times(1)).addLoan("Big Bank", loanDetails);
    }
}