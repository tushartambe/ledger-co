package org.ledgerco.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ledgerco.model.LoanDetails;
import org.ledgerco.repository.Repository;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BalanceServiceTest {

    Repository repository;
    BalanceService balanceService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
        repository = Mockito.mock(Repository.class);
        balanceService = new BalanceService(repository);
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void shouldReturnBalanceForLoanWithZeroInstallments() {
        LoanDetails loanDetails = new LoanDetails("Elon", 2000, 2, 2);
        when(repository.getLoanDetails(any(), any())).thenReturn(loanDetails);

        balanceService.process("MBI", "Elon", 0);

        verify(repository, times(1)).getLoanDetails(any(), any());
        assertEquals("MBI Elon 0 24", outContent.toString().trim());
    }

    @Test
    void shouldReturnBalanceForLoanWithMultipleInstallments() {
        LoanDetails loanDetails = new LoanDetails("Dale", 10000, 5, 4);
        when(repository.getLoanDetails(any(), any())).thenReturn(loanDetails);

        balanceService.process("IDIDI", "Dale", 5);

        verify(repository, times(1)).getLoanDetails(any(), any());
        assertEquals("IDIDI Dale 1000 55", outContent.toString().trim());
    }
}