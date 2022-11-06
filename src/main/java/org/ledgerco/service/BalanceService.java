package org.ledgerco.service;

import org.ledgerco.Exception.LoanDetailsNotFoundException;
import org.ledgerco.model.LoanDetails;
import org.ledgerco.model.PaymentDetails;
import org.ledgerco.repository.Repository;

import java.util.Optional;

public class BalanceService {
    private final Repository repository;

    public BalanceService(Repository repository) {
        this.repository = repository;
    }

    public void process(String bank, String customer, Integer numberOfInstallmentsPaid) {
        Optional<LoanDetails> loanDetailsOptional = repository.getLoanDetails(bank, customer);
        if (loanDetailsOptional.isEmpty()) {
            throw new LoanDetailsNotFoundException("Loan details not found for bank : " + bank + "and cutomer : " + customer);
        }
        LoanDetails loanDetails = loanDetailsOptional.get();

        Double installmentAmount = loanDetails.getInstallmentAmount();
        Integer totalNumberOfInstallments = loanDetails.getTotalNumberOfInstallments();

        double totalAmountPaid = installmentAmount * numberOfInstallmentsPaid;
        int remainingInstallments = totalNumberOfInstallments - numberOfInstallmentsPaid;

        Optional<PaymentDetails> paymentDetailsOptional = repository.getPaymentDetails(bank, customer);
        if (paymentDetailsOptional.isPresent()) {
            PaymentDetails paymentDetails = paymentDetailsOptional.get();
            if (paymentDetails.installmentNumber <= numberOfInstallmentsPaid) {
                totalAmountPaid = totalAmountPaid + paymentDetails.paymentAmount;
                double remainingAmount = loanDetails.getTotalAmountToRepay() - totalAmountPaid;
                remainingInstallments = (int) Math.ceil(remainingAmount / installmentAmount);
            }
        }

        String entry = bank +
                " " +
                customer +
                " " +
                (int) totalAmountPaid +
                " " +
                remainingInstallments;

        System.out.println(entry);
    }
}
