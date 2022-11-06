package org.ledgerco.service;

import org.ledgerco.model.LoanDetails;
import org.ledgerco.repository.Repository;

public class BalanceService {
    private final Repository repository;

    public BalanceService(Repository repository) {
        this.repository = repository;
    }

    public void process(String bank, String customer, Integer numberOfInstallmentsPaid) {
        LoanDetails loanDetails = repository.getLoanDetails(bank, customer);

        Double installmentAmount = loanDetails.getInstallmentAmount();
        Integer totalNumberOfInstallments = loanDetails.getTotalNumberOfInstallments();

        double totalAmountPaid = installmentAmount * numberOfInstallmentsPaid;
        Integer remainingInstallments = totalNumberOfInstallments - numberOfInstallmentsPaid;

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
