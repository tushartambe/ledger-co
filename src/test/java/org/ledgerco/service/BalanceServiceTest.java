package org.ledgerco.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ledgerco.Exception.LoanDetailsNotFoundException;
import org.ledgerco.model.LoanDetails;
import org.ledgerco.model.PaymentDetails;
import org.ledgerco.repository.Repository;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
        when(repository.getLoanDetails(any(), any())).thenReturn(Optional.of(loanDetails));

        balanceService.process("MBI", "Elon", 0);

        verify(repository, times(1)).getLoanDetails(any(), any());
        assertEquals("MBI Elon 0 24", outContent.toString().trim());
    }

    @Test
    void shouldReturnBalanceForLoanWithMultipleInstallments() {
        LoanDetails loanDetails = new LoanDetails("Dale", 10000, 5, 4);
        when(repository.getLoanDetails(any(), any())).thenReturn(Optional.of(loanDetails));

        balanceService.process("IDIDI", "Dale", 5);

        verify(repository, times(1)).getLoanDetails(any(), any());
        assertEquals("IDIDI Dale 1000 55", outContent.toString().trim());
    }

    @Test
    void shouldReturnBalanceForLoanWithLumsumPayment() {
        LoanDetails loanDetails = new LoanDetails("Dale", 5000, 1, 6);
        PaymentDetails paymentDetails = new PaymentDetails("Dale", 1000, 5);
        when(repository.getLoanDetails(any(), any())).thenReturn(Optional.of(loanDetails));
        when(repository.getPaymentDetails(any(), any())).thenReturn(Optional.of(paymentDetails));

        balanceService.process("IDIDI", "Dale", 6);

        verify(repository, times(1)).getLoanDetails(any(), any());
        assertEquals("IDIDI Dale 3652 4", outContent.toString().trim());
    }

    @Test
    void shouldThrowExceptionIfLoanDetailsNotFound() {
        when(repository.getLoanDetails(any(), any())).thenReturn(Optional.empty());

        assertThrows(LoanDetailsNotFoundException.class, () -> balanceService.process("MBI", "Elon", 0));
    }
}