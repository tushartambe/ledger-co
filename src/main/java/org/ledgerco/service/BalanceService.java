package org.ledgerco.service;

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
        LoanDetails loanDetails = loanDetailsOptional.get();

        Double installmentAmount = loanDetails.getInstallmentAmount();
        Integer totalNumberOfInstallments = loanDetails.getTotalNumberOfInstallments();

        double totalAmountPaid = installmentAmount * numberOfInstallmentsPaid;
        Integer remainingInstallments = totalNumberOfInstallments - numberOfInstallmentsPaid;

        Optional<PaymentDetails> paymentDetailsOptional = repository.getPaymentDetails(bank, customer);
        if (paymentDetailsOptional.isPresent()) {
            PaymentDetails paymentDetails = paymentDetailsOptional.get();
            if (paymentDetails.installmentNumber <= numberOfInstallmentsPaid) {
                totalAmountPaid = totalAmountPaid + paymentDetails.paymentAmount;
                double remainingAmount = loanDetails.getTotalAmountToRepay() - totalAmountPaid;
                remainingInstallments = (int) Math.ceil(remainingAmount / installmentAmount);
            }
        }

        StringBuilder entry = new StringBuilder();
        entry.append(bank)
                .append(" ")
                .append(customer)
                .append(" ")
                .append((int) totalAmountPaid)
                .append(" ")
                .append(remainingInstallments);

        System.out.println(entry);
    }
}
